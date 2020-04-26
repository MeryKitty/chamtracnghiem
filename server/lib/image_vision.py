from matplotlib import pyplot as plt

from . import preprocess as pre
from . import character_recognize as model

def extract(image):
    result = {}
    min_slice = image.shape[1] // 100
    column_break = image.shape[1] // 5
    # plt.imshow(image, interpolation = 'nearest')
    # plt.show()
    image = pre.rgb_to_gray(image)
    # plt.imshow(image, interpolation = 'nearest')
    # plt.show()
    image = pre.normalize(image)
    # pre.print_pixel(image)
    # plt.imshow(image, interpolation = 'nearest')
    # plt.show()
    lines = pre.cut_horizontal(image, min_slice = min_slice)
    for line in lines:
        # plt.imshow(line, interpolation = 'nearest')
        # plt.show()
        answers = pre.cut_white_vertical(line, min_slice = column_break)
        for answer in answers:
            characters = pre.cut_vertical(answer, min_slice = min_slice)
            answer_string = []
            for character in characters:
                # plt.imshow(character, interpolation = 'nearest')
                # plt.show()
                character = pre.resize(character, 30, 30)
                character = character.reshape(1, 30, 30, 1)
                answer_string.append(model.predict(character))
            answer_string = ''.join(answer_string)
            try:
                if answer_string[-1] in 'abcd':
                    value = answer_string[-1]
                    key = int(answer_string[:-1])
                else:
                    value = ''
                    key = int(answer_string)
                result[key] = value
            except:
                pass
    return result
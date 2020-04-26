import math

import numpy as np
from PIL import Image
from matplotlib import cm
from matplotlib import pyplot as plt

positive = 0.4

def rgb_to_gray(rgb):
    return rgb[:,:,0] * 0.2989 + rgb[:,:,1] * 0.5870 + rgb[:,:,2] * 0.1140

def cmk_to_gray(cmyk):
    c = cmyk[:,:,0]
    m = cmyk[:,:,1]
    y = cmyk[:,:,2]
    k = cmyk[:,:,3]
    return (1 - c) * 0.2989 + (1 - m) * 0.5870 + (1 - y) * 0.1140

def normalize(image):
    max_colour = np.max(image)
    min_colour = np.min(image)
    return (image - min_colour) / (max_colour - min_colour)

def cut_horizontal(image, min_slice = 10):
    result_slice = []
    current = -1
    for i in range(image.shape[0]):
        temp_slice = image[i, :]
        slice_min = np.min(temp_slice)
        if slice_min <= positive:
            if current < 0:
                current = i
            if i == image.shape[0] - 1:
                if current >= 0 and i - current >= min_slice - 1:
                    result_slice.append((current, i + 1))
                break
        else:
            if current >= 0:
                if i - current >= min_slice:
                    result_slice.append((current, i))
                current = -1
    result = []
    for up, down in result_slice:
        result.append(image[up:down, :])
    return result    

def cut_vertical(image, min_slice = 10):
    result_slice = []
    current = -1
    for i in range(image.shape[1]):
        temp_slice = image[:, i]
        slice_min = np.min(temp_slice)
        if slice_min <= positive:
            if current < 0:
                current = i
            if i == image.shape[1] - 1:
                if current >= 0 and i - current >= min_slice - 1:
                    result_slice.append((current, i + 1))
                break
        else:
            if current >= 0:
                if i - current >= min_slice:
                    result_slice.append((current, i))
                current = -1
    result = []
    for left, right in result_slice:
        result.append(image[:, left:right])
    return result

def cut_white_vertical(image, min_slice = 100):
    cut_slice = []
    current = -1
    for i in range(image.shape[1]):
        temp_slice = image[:, i]
        slice_min = np.min(temp_slice)
        if slice_min > positive:
            if current < 0:
                current = i
            if i == image.shape[1] - 1:
                if current >= 0 and i - current >= min_slice - 1:
                    cut_slice.append((current, image.shape[1]))
                break
        else:
            if current >= 0:
                if i - current >= min_slice:
                    cut_slice.append((current, i))
                current = -1
    # print(cut_slice)
    if len(cut_slice) == 0:
        result_slice = [(0, image.shape[1])]
    else:
        result_slice = []
        for i in range(len(cut_slice)):
            if i == 0:
                result_slice.append((0, cut_slice[0][0]))
            else:
                result_slice.append((cut_slice[i - 1][1], cut_slice[i][0]))
            result_slice.append((cut_slice[-1][1], image.shape[1]))
    result = []
    for left, right in result_slice:
        if left < right:
            result.append(image[:, left:right])
    return result

def resize(image, target_width, target_height):
    if target_height / image.shape[0] > target_width / image.shape[1]:
        top_height = math.floor((target_height - image.shape[0] * target_width / image.shape[1]) / 2)
        temp_top = np.ones((top_height, image.shape[1]))
        image = np.concatenate((temp_top, image, temp_top), axis = 0)
    else:
        left_width = math.floor((target_width - image.shape[1] * target_height / image.shape[0]) / 2)
        temp_left = np.ones((image.shape[0], left_width))
        image = np.concatenate((temp_left, image, temp_left), axis = 1)
    temp = Image.fromarray(np.uint8(cm.gist_earth(image) * 255))
    temp = temp.resize((target_width, target_height), resample = Image.BICUBIC)
    # temp.show()
    temp = np.asarray(temp).astype(np.float32) / 255
    # print(temp.shape)
    temp = rgb_to_gray(temp[:,:,:-1])
    # plt.imshow(temp, interpolation='nearest')
    # plt.show()
    return temp

def print_pixel(image):
    output = []
    for i in range(image.shape[0]):
        temp_output = []
        for j in range(image.shape[1]):
            if image[i, j] <= positive or i == 0 or i == image.shape[0] - 1 or j == 0 or j == image.shape[1] - 1:
                temp_output.append('{0:4.2f}'.format(image[i, j]))
            else:
                temp_output.append('    ')
        output.append(' '.join(temp_output))
    print('\n'.join(output))

# def process(image):
#     temp = normalize(image)
#     temp = 1 - temp
#     # print_pixel(temp)
#     temp_list = cut_vertical(temp, min_slice = 2)
#     temp = temp_list[0]
#     for i, temp_slice in enumerate(temp_list):
#         if temp_list[i].shape[1] > temp.shape[1]:
#             temp = temp_list[i]
#     # print_pixel(temp)
#     temp_list = cut_horizontal(temp, min_slice = 2)
#     temp = temp_list[0]
#     for i, temp_slice in enumerate(temp_list):
#         if temp_list[i].shape[0] > temp.shape[0]:
#             temp = temp_list[i]
#     # print_pixel(temp)
#     temp = resize(temp, 30, 30)
#     # plt.imshow(temp, interpolation='nearest')
#     # plt.show()
#     return temp
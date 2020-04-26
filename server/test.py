import base64

import numpy as np
import requests
from PIL import Image

url = 'http://localhost:1999'

def test():
    image = Image.open('demo.jpg')
    image = np.asarray(image)
    image = image * 255
    image = image.astype(np.uint8)
    temp = image[:, :, -1].reshape(image.shape[0], image.shape[1], 1)
    image = np.concatenate((temp, image[:, :, :-1]), axis = 2)
    image = base64.standard_b64encode(image.tobytes())
    image = image.decode(encoding = 'utf8')
    # print(image)
    correct = {1: 'A', 2: 'B', 3: 'D', 4: 'A', 5: 'C', 6: 'D', 7: 'A', 8: 'C', 9: 'A', 10: 'C'}
    response = requests.post(url, json = {'image': image, 'correct': correct})
    print(response.status_code)
    print(response.json().get('score'))

if __name__ == "__main__":
    test()
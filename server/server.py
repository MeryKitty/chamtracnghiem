import base64

import numpy as np
from flask import Flask, request
from PIL import Image

import lib

url = '192.168.43.106'
app = Flask(__name__)

@app.route('/', methods = ['POST'])
def score():
    json = request.get_json()
    if json is None:
        return jsonify(score = 0), 400
    try:
        image_height = json.get('image_height')
        image_width = json.get('image_width')
        image = base64.standard_b64decode(json.get('image'))
        image = np.frombuffer(image, dtype = np.uint8)
        image = image.reshape(image_height, image_width, 3)
        correct = json.get('correct')
        answer = lib.extract(image)
        score = lib.compare(correct, answer)
        return jsonify(score = score), 200
    except KeyError:
        return jsonify(score = 0), 400
    except Exception as e:
        print(e)
        return jsonify(score = 0), 500

if __name__ == "__main__":
    app.run(host = url, port = 1999)
    # image = Image.open('demo.jpg')
    # image = np.asarray(image)
    # image = 1 - image
    # image = image * 255
    # image = image.astype(np.uint8)
    # answer = lib.extract(image[:, :, :])
    # print(answer)
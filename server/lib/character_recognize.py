from tensorflow.keras.models import load_model
import numpy as np

model = load_model('model/character_model.tf')

def label_to_result(vector):
    if not isinstance(vector, np.ndarray):
        raise TypeError
    label = np.argmax(vector)
    confidence = vector[:, label]
    print(label)
    print(confidence)
    if label < 0 or label > 46:
        raise ValueError
    elif label < 10:
        return str(label)
    elif label < 35:
        return chr(label - 10 + 65)
    else:
        if label == 35:
            return 'a'
        elif label == 36:
            return 'b'
        elif label == 37:
            return 'd'
        else:
            return ''

def predict(processed_image):
    label = model.predict(processed_image)
    return label_to_result(label).lower()
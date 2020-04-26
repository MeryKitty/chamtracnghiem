def compare(answer, correct):
    result = 0
    for key, value in correct.items():
        temp = answer.get(key, None)
        if temp == value:
            result += 1
    return result
def insertion_sort(an_array):
    for i in range(1, an_array.__len__()):
        key = an_array[i]
        j = i - 1
        while j >= 0 and an_array[j] > key:
            an_array[j + 1] = an_array[j]
            j -= 1
        an_array[j + 1] = key
    return an_array


def selection_sort(an_array):
    for i in range(an_array.__len__()):
        min_index = i
        for j in range(i + 1, an_array.__len__()):
            if an_array[j] < an_array[i]:
                min_index = j
        an_array[i], an_array[min_index] = an_array[min_index], an_array[i]
    return an_array





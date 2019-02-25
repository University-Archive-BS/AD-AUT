import timeit
import matplotlib.pyplot as plt


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


number = [5, 10, 20, 35, 55, 80, 105]
runtime = []
start1 = timeit.default_timer()
print(insertion_sort([1, 99, 65, 54, 12]))
stop1 = timeit.default_timer()
print('Runtime: ', (stop1 - start1) * 100000)
runtime.append((stop1 - start1) * 100000)
start2 = timeit.default_timer()
print(insertion_sort([57, 44, 100, 27, 51, 2, 8, 95, 67, 99]))
stop2 = timeit.default_timer()
print('Runtime: ', (stop2 - start2) * 100000)
runtime.append((stop2 - start2) * 100000)
start3 = timeit.default_timer()
print(insertion_sort([62, 56, 79, 76, 42, 95, 52, 75, 40, 61, 68, 90, 64, 54, 27, 92, 99, 48, 83, 58]))
stop3 = timeit.default_timer()
print('Runtime: ', (stop3 - start3) * 100000)
runtime.append((stop3 - start3) * 100000)
start4 = timeit.default_timer()
print(insertion_sort(
    [32, 26, 35, 40, 8, 78, 49, 34, 60, 61, 98, 39, 25, 18, 77, 41, 89, 91, 15, 64, 88, 16, 14, 17, 71, 21, 30, 79, 22,
     94, 56, 11, 69, 47, 7]))
stop4 = timeit.default_timer()
print('Runtime: ', (stop4 - start4) * 100000)
runtime.append((stop4 - start4) * 100000)
start5 = timeit.default_timer()
print(insertion_sort(
    [196, 36, 121, 5, 120, 24, 2, 169, 97, 61, 195, 150, 76, 176, 68, 86, 59, 167, 93, 58, 200, 92, 187, 94, 182, 16,
     18, 22, 118, 126, 27, 170,
     149, 64, 8, 83, 189, 60, 153, 40, 77, 117, 56, 192, 87, 199, 75, 135, 51, 57, 45, 106, 3, 164, 122]))
stop5 = timeit.default_timer()
print('Runtime: ', (stop5 - start5) * 100000)
runtime.append((stop5 - start5) * 100000)
start6 = timeit.default_timer()
print(insertion_sort(
    [31, 208, 1, 94, 54, 253, 204, 218, 132, 41, 262, 159, 153, 300, 290, 152, 270, 16, 42, 269, 265, 293, 289, 267, 97,
     137, 133, 226, 294, 298,
     240, 63, 180, 55, 196, 139, 62, 12, 60, 235, 213, 101, 239, 76, 2, 197, 258, 82, 283, 68, 299, 228, 52, 93, 140,
     278, 19, 70, 231, 27, 205,
     260, 162, 223, 214, 206, 66, 271, 160, 268, 40, 183, 246, 88, 181, 292, 124, 143, 233, 11]))
stop6 = timeit.default_timer()
print('Runtime: ', (stop6 - start6) * 100000)
runtime.append((stop6 - start6) * 100000)
start7 = timeit.default_timer()
print(insertion_sort(
    [210, 268, 280, 94, 149, 181, 200, 156, 194, 121, 118, 50, 259, 28, 58, 213, 80, 239, 15, 6, 87, 167, 230, 150, 217,
     247, 110, 31, 102, 9, 283, 25, 222, 35, 65, 249, 89, 263, 206, 81, 254, 113, 142, 193, 48, 159, 38, 60, 229, 227,
     252, 57, 195, 243, 191, 232,
     294, 101, 49, 27, 251, 228, 109, 175, 23, 76, 292, 170, 4, 18, 74, 146, 79,
     285, 42, 141, 91, 1, 54, 274, 184, 245, 53, 83, 29, 226, 55, 3, 66, 30, 147, 219, 13, 199, 205, 218, 36, 61, 250,
     260, 277, 208, 63, 10, 114
     ]))
stop7 = timeit.default_timer()
print('Runtime: ', (stop7 - start7) * 100000)
runtime.append((stop7 - start7) * 100000)
plt.xlabel('Number of Inputs')
plt.ylabel('Run time in micro second')
plt.plot(number, runtime)
plt.savefig('plot.png')
plt.show()

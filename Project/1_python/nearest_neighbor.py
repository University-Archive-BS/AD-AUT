import numpy as np
from math import hypot as hp


def nearest_neighbor():
    points[0][2] = 1
    present_point = 0
    result_path = [present_point + 1]
    while np.sum(points, axis=0)[2] != n:
        present_point = find_next_nearest_neighbor(present_point)
        points[present_point][2] = 1
        result_path.append(present_point + 1)
    return result_path


def find_next_nearest_neighbor(present_location):
    min_distance = float("inf")
    selected_point = 0
    for k in range(1, n):
        if points[k][2] == 1:
            continue
        d = hp(points[present_location][0] - points[k][0], points[present_location][1] - points[k][1])
        if d < min_distance:
            min_distance = d
            selected_point = k
    return selected_point


def fill_array_by_file(file):
    vertices = np.zeros((n, 3))
    for line, i in zip(file, range(n)):
        a_line = str(line).split(' ')
        vertices[i][0] = int(a_line[0])
        vertices[i][1] = int(a_line[1])
    return vertices


f = open("points.txt", "r")
n = int(f.readline())
points = fill_array_by_file(f)
result = nearest_neighbor()
print(result)

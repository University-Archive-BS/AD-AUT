import numpy as np
from math import hypot as hp


def find_next_nearest_neighbor(present_location):
    min_distance = float("inf")
    selected_point = 0
    for k in range(1, n):
        if vertices[k][2] == 1:
            continue
        d = hp(vertices[present_location][0] - vertices[k][0], vertices[present_location][1] - vertices[k][1])
        if d < min_distance:
            min_distance = d
            selected_point = k
    return selected_point


f = open("points.txt", "r")

n = int(f.readline())
vertices = np.zeros((n, 3))

for line, i in zip(f, range(n)):
    a_line = str(line).split(' ')
    vertices[i][0] = int(a_line[0])
    vertices[i][1] = int(a_line[1])

vertices[0][2] = 1
present_point = 0
print(present_point)
while np.sum(vertices, axis=0)[2] != n:
    present_point = find_next_nearest_neighbor(present_point)
    vertices[present_point][2] = 1
    print(present_point)

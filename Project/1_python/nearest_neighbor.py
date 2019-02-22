import numpy as np

f = open("points.txt", "r")

n = int(f.readline())
vertices = np.zeros((n, 3))

for line, i in zip(f, range(n)):
    a_line = str(line).split(' ')
    vertices[i][0] = int(a_line[0])
    vertices[i][1] = int(a_line[1])

print(vertices)


import numpy as np

f = open("points.txt", "r")

n = int(f.readline())
vertices = np.zeros((n, 2))

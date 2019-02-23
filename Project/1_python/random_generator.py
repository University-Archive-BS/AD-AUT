import random

f = open("random.txt", "w+")
n = random.randint(3, 6)
f.write(str(n) + '\n')
for i in range(n):
    f.write(str(random.randint(0, 60)) + ' ' + str(random.randint(0, 60)) + '\n')

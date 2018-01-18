file_input = file("input_dir/input_mesh.txt", "w")
ids = [1, 2, 3, 4, 5, 6, 7, 8, 9]
directions = [0, 1, 2, 3, 4, 5, 6, 7, 8]

for id in ids:
   for direction in directions:
       if (id == 5):
           file_input.write(str(id) + str(direction) + " 1" + "\n")
       else:
           file_input.write(str(id) + str(direction) + " 0" + "\n")

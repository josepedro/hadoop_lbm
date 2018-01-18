import numpy as np
import sys

def getIdPosition(id, number_lines, number_rows):
	idCount = 1
	for line in xrange(0, number_lines):
		for row in xrange(0, number_rows):
			if idCount == id:
				return [line, row]
			else:
				idCount += 1

def getPositionId(lineId, rowId, number_lines, number_rows):
	idCount = 1
	for line in xrange(0, number_lines):
		for row in xrange(0, number_rows):
			if line == lineId and row == rowId:
				return idCount
			else:
				idCount += 1

def get1(line, row, number_lines, number_rows):
	if line == 0 and row == 0:
		return [number_lines - 1, row]
	if line == 0 and row == number_rows - 1:
		return [number_lines - 1, number_rows - 1]
	if line == 0 and row > 0 and row < number_rows - 1:
		return [number_lines - 1, row]
	else:
		return [line - 1, row]

def get1_id(id, number_lines, number_rows):
	position_id = getIdPosition(id, number_lines, number_rows)
	position_neighborhood = get1(position_id[0], position_id[1], number_lines, number_rows)
	return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows)

def get2(line, row, number_lines, number_rows):
	if line == 0 and row == 0:
		return [number_lines - 1, row + 1]
	if line == 0 and row == number_rows - 1:
		return [number_lines - 1, 0]
	if line == number_lines - 1 and row == number_rows - 1:
		return [number_lines - 2, 0]
	if line == 0 and row > 0 and row < number_rows - 1:
		return [number_lines - 1, row + 1]
	if line > 0 and line < number_lines - 1 and row == number_rows - 1:
		return [line - 1, 0]
	else:
		return [line - 1, row + 1]

def get2_id(id, number_lines, number_rows):
	position_id = getIdPosition(id, number_lines, number_rows)
	position_neighborhood = get2(position_id[0], position_id[1], number_lines, number_rows)
	return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows)

def get3(line, row, number_lines, number_rows):
	if line == 0 and row == number_rows - 1:
		return [line, 0]
	if line == number_lines - 1 and row == number_rows - 1:
		return [number_lines - 1, 0]
	if line > 0 and line < number_lines - 1 and row == number_rows - 1:
		return [line, 0]
	else:	
		return [line, row + 1]

def get3_id(id, number_lines, number_rows):
	position_id = getIdPosition(id, number_lines, number_rows)
	position_neighborhood = get3(position_id[0], position_id[1], number_lines, number_rows)
	return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows)

def get4(line, row, number_lines, number_rows):
	if line == 0 and row == number_rows - 1:
		return [1, 0]
	if line == number_lines - 1 and row == number_rows - 1:
		return [0, 0]
	if line == number_lines - 1 and row == 0:
		return [0, 1]
	if line > 0 and line < number_lines - 1 and row == number_rows - 1:
		return [line + 1, 0]
	if line == number_lines - 1 and row > 0 and row < number_rows - 1:
		return [0, row + 1]  
	else:
		return [line + 1, row + 1]

def get4_id(id, number_lines, number_rows):
	position_id = getIdPosition(id, number_lines, number_rows)
	position_neighborhood = get4(position_id[0], position_id[1], number_lines, number_rows)
	return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows)

def get5(line, row, number_lines, number_rows):
	if line == number_lines - 1 and row == 0:
		return [0, row]
	if line == number_lines - 1 and row == number_rows - 1:
		return [0, row]
	if line == number_lines - 1 and row > 0 and row < number_rows - 1:
		return [0, row]
	else:	
		return [line + 1, row]

def get5_id(id, number_lines, number_rows):
	position_id = getIdPosition(id, number_lines, number_rows)
	position_neighborhood = get5(position_id[0], position_id[1], number_lines, number_rows)
	return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows)

def get6(line, row, number_lines, number_rows):
	if line == 0 and row == 0:
		return [line + 1, number_rows - 1]
	if line ==  number_lines - 1 and row == 0:
		return [0, number_rows - 1]
	if line == number_lines - 1 and row == number_rows - 1:
		return [0, row - 1]
	if line > 0 and line < number_lines - 1 and row == 0:
		return [line + 1, number_rows - 1]
	if line == number_lines - 1 and row > 0 and row < number_rows - 1:
		return [0, row - 1] 
	else:	
		return [line + 1, row - 1]

def get6_id(id, number_lines, number_rows):
	position_id = getIdPosition(id, number_lines, number_rows)
	position_neighborhood = get6(position_id[0], position_id[1], number_lines, number_rows)
	return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows)

def get7(line, row, number_lines, number_rows):
	if line == 0 and row == 0:
		return [line, number_rows - 1]
	if line == number_lines - 1 and row == 0:
		return [line, number_rows - 1]
	if line > 0 and line < number_lines - 1 and row == 0:
		return [line, number_rows - 1]
	else:
		return [line, row - 1]

def get7_id(id, number_lines, number_rows):
	position_id = getIdPosition(id, number_lines, number_rows)
	position_neighborhood = get7(position_id[0], position_id[1], number_lines, number_rows)
	return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows)

def get8(line, row, number_lines, number_rows):
	if line == 0 and row == 0:
		return [number_lines - 1, number_rows - 1]
	if line == 0 and row == number_rows - 1:
		return [number_lines - 1, row - 1]
	if line == number_lines - 1 and row == 0:
		return [line - 1, number_rows - 1]
	if line > 0 and line < number_lines - 1 and row == 0:
		return [line - 1, number_rows - 1]
	if line == 0 and row > 0 and row < number_rows - 1:
		return [number_lines - 1, row - 1]
	else:
		return [line - 1, row - 1]

def get8_id(id, number_lines, number_rows):
	position_id = getIdPosition(id, number_lines, number_rows)
	position_neighborhood = get8(position_id[0], position_id[1], number_lines, number_rows)
	return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows)

print "10, %d" % 10
print "11, %d1" % get1_id(1,3,3)
print "12, %d2" % get2_id(1,3,3)
print "13, %d3" % get3_id(1,3,3)
print "14, %d4" % get4_id(1,3,3)
print "15, %d5" % get5_id(1,3,3)
print "16, %d6" % get6_id(1,3,3)
print "17, %d7" % get7_id(1,3,3)
print "18, %d8" % get8_id(1,3,3)
print "20, %d" % 20
print "21, %d1" % get1_id(2,3,3)
print "22, %d2" % get2_id(2,3,3)
print "23, %d3" % get3_id(2,3,3)
print "24, %d4" % get4_id(2,3,3)
print "25, %d5" % get5_id(2,3,3)
print "26, %d6" % get6_id(2,3,3)
print "27, %d7" % get7_id(2,3,3)
print "28, %d8" % get8_id(2,3,3)
print "30, %d" % 30
print "31, %d1" % get1_id(3,3,3)
print "32, %d2" % get2_id(3,3,3)
print "33, %d3" % get3_id(3,3,3)
print "34, %d4" % get4_id(3,3,3)
print "35, %d5" % get5_id(3,3,3)
print "36, %d6" % get6_id(3,3,3)
print "37, %d7" % get7_id(3,3,3)
print "38, %d8" % get8_id(3,3,3)
print "40, %d" % 40
print "41, %d1" % get1_id(4,3,3)
print "42, %d2" % get2_id(4,3,3)
print "43, %d3" % get3_id(4,3,3)
print "44, %d4" % get4_id(4,3,3)
print "45, %d5" % get5_id(4,3,3)
print "46, %d6" % get6_id(4,3,3)
print "47, %d7" % get7_id(4,3,3)
print "48, %d8" % get8_id(4,3,3)
print "50, %d" % 50
print "51, %d1" % get1_id(5,3,3)
print "52, %d2" % get2_id(5,3,3)
print "53, %d3" % get3_id(5,3,3)
print "54, %d4" % get4_id(5,3,3)
print "55, %d5" % get5_id(5,3,3)
print "56, %d6" % get6_id(5,3,3)
print "57, %d7" % get7_id(5,3,3)
print "58, %d8" % get8_id(5,3,3)
print "60, %d" % 60
print "61, %d1" % get1_id(6,3,3)
print "62, %d2" % get2_id(6,3,3)
print "63, %d3" % get3_id(6,3,3)
print "64, %d4" % get4_id(6,3,3)
print "65, %d5" % get5_id(6,3,3)
print "66, %d6" % get6_id(6,3,3)
print "67, %d7" % get7_id(6,3,3)
print "68, %d8" % get8_id(6,3,3)
print "70, %d" % 70
print "71, %d1" % get1_id(7,3,3)
print "72, %d2" % get2_id(7,3,3)
print "73, %d3" % get3_id(7,3,3)
print "74, %d4" % get4_id(7,3,3)
print "75, %d5" % get5_id(7,3,3)
print "76, %d6" % get6_id(7,3,3)
print "77, %d7" % get7_id(7,3,3)
print "78, %d8" % get8_id(7,3,3)
print "80, %d" % 80
print "81, %d1" % get1_id(8,3,3)
print "82, %d2" % get2_id(8,3,3)
print "83, %d3" % get3_id(8,3,3)
print "84, %d4" % get4_id(8,3,3)
print "85, %d5" % get5_id(8,3,3)
print "86, %d6" % get6_id(8,3,3)
print "87, %d7" % get7_id(8,3,3)
print "88, %d8" % get8_id(8,3,3)
print "90, %d" % 90
print "91, %d1" % get1_id(9,3,3)
print "92, %d2" % get2_id(9,3,3)
print "93, %d3" % get3_id(9,3,3)
print "94, %d4" % get4_id(9,3,3)
print "95, %d5" % get5_id(9,3,3)
print "96, %d6" % get6_id(9,3,3)
print "97, %d7" % get7_id(9,3,3)
print "98, %d8" % get8_id(9,3,3)
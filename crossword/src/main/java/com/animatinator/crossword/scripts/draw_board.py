import sys


def main(board_to_print):
    print(board_to_print)


if __name__ == '__main__':
    args = sys.argv
    if len(args) < 2:
        print("ERROR: Need to supply a board to be printed!")
    elif len(args) > 2:
        print("ERROR: Too many args! This script only takes one argument, the board to be printed.")
    else:
        main(args[1])

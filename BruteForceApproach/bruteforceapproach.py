import os.path
import sys

dictionary = {
    "values": [],
    "weights": [],
    "knapsack_weight": 0,
    "path": os.path.join("../DataSet", "1-Easy"),
    "value_filename": "value_file.txt",
    "weight_filename": "weight_file.txt",
    "knapsack_filename": "knapsack_file.txt"
}


def fill_the_dictionary(hardness_level):
    match hardness_level:
        case 1:
            dictionary["path"] = os.path.join("../DataSet", "1-Easy")
        case 2:
            dictionary["path"] = os.path.join("../DataSet", "2-Medium")
        case 3:
            dictionary["path"] = os.path.join("../DataSet", "3-Hard")

    with open(os.path.join(dictionary["path"], dictionary["value_filename"]), "r") as file:
        for line in file:
            dictionary["values"].append(int(line.strip().split("\n")[0]))

    with open(os.path.join(dictionary["path"], dictionary["weight_filename"]), "r") as file:
        for line in file:
            dictionary["weights"].append(int(line.strip().split("\n")[0]))

    with open(os.path.join(dictionary["path"], dictionary["value_filename"]), "r") as file:
        dictionary["knapsack_weight"] = int(file.read().strip().split("\n")[0])


def solve_knapsack(profits, weights, capacity):
    return knapsack_recursive(profits, weights, capacity, 0)


def knapsack_recursive(profits, weights, capacity, currentIndex):
    # base checks
    if capacity <= 0 or currentIndex >= len(profits):
        return 0

    # recursive call after choosing the element at the currentIndex
    # if the weight of the element at currentIndex exceeds the capacity, we  shouldn't process this
    profit1 = 0
    if weights[currentIndex] <= capacity:
        profit1 = profits[currentIndex] + knapsack_recursive(
            profits, weights, capacity - weights[currentIndex], currentIndex + 1)

    # recursive call after excluding the element at the currentIndex
    profit2 = knapsack_recursive(profits, weights, capacity, currentIndex + 1)

    return max(profit1, profit2)


def main():
    sys.setrecursionlimit(10000)
    fill_the_dictionary(3)
    print(solve_knapsack(dictionary["values"], dictionary["weights"],
                         dictionary["knapsack_weight"]))

    # In python the max recursion limit is 1000 and the hardness level of 3 exceeds it.
    # sys.setrecursionlimit(10000)


main()

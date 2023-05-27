import os.path
import sys
import time

import matplotlib.pyplot as plt

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

    with open(os.path.join(dictionary["path"], dictionary["knapsack_filename"]), "r") as file:
        dictionary["knapsack_weight"] = int(file.read().strip().split("\n")[0])


def special_dataset_for_plot(number):
    dictionary["path"] = os.path.join("../DataSet/DifferentDataSetsForPlots", str(number))
    with open(os.path.join(dictionary["path"], dictionary["value_filename"]), "r") as file:
        for line in file:
            dictionary["values"].append(int(line.strip().split("\n")[0]))

    with open(os.path.join(dictionary["path"], dictionary["weight_filename"]), "r") as file:
        for line in file:
            dictionary["weights"].append(int(line.strip().split("\n")[0]))

    with open(os.path.join(dictionary["path"], dictionary["knapsack_filename"]), "r") as file:
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


def draw_timecomplexity_plot():
    execution_timeof_dataset = []
    dataset_length = []

    for x in range(1, 8):
        start_time = time.time()
        special_dataset_for_plot(x)
        solve_knapsack(dictionary["values"], dictionary["weights"], dictionary["knapsack_weight"])
        end_time = time.time()
        execution_timeof_dataset.append(end_time - start_time)
        dataset_length.append(len(dictionary["values"]))
        print(len(dictionary["values"]))

        dictionary["values"].clear()
        dictionary["weights"].clear()

    plt.plot(dataset_length, execution_timeof_dataset, marker='o')
    plt.xlabel('Input Size')
    plt.ylabel('Execution Time')
    plt.title('10 Different Data Set\nKnapsack Algorithm Time Complexity in BruteForce Approach')
    plt.show()


def main():
    # In python the max recursion limit is 1000 and the hardness level of 3 exceeds it.
    # sys.setrecursionlimit(10000) but It couldn't solve it still

    # draw_timecomplexity_plot()

    special_dataset_for_plot(8)
    # print(dictionary["values"])
    # print(dictionary["weights"])
    # print(dictionary["knapsack_weight"])
    # print(solve_knapsack(dictionary["values"], dictionary["weights"], dictionary["knapsack_weight"]))

    print(sum(dictionary["weights"]))

main()

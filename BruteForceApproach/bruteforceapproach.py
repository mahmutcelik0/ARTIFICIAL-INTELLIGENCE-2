import os.path
import time
import sys

import matplotlib.pyplot as plt

dictionary = {
    "values": [],
    "weights": [],
    "knapsack_weight": 0,
    "path": "",
}


def fill_the_dictionary(file_name, dataset_number):
    dictionary["path"] = os.path.join("../DataSet/EXAMPLE_DATASET/", file_name)

    with open(dictionary["path"], "r") as file:

        number = 0
        for line in file:
            if int(number / 3) == dataset_number:
                for a in line.strip().split(" "):
                    match number % 3:
                        case 0:
                            dictionary["values"].append(int(a))
                        case 1:
                            dictionary["weights"].append(int(a))
                        case 2:
                            dictionary["knapsack_weight"] = int(a)
                number = number + 1
            else:
                number = number + 1


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


def draw_timecomplexity_plot(filename, number):
    execution_timeof_dataset = []
    dataset_length = []

    for x in range(number + 1):
        start_time = time.time()
        fill_the_dictionary(filename, x)
        print(dictionary)
        dataset_length.append(len(dictionary["values"]))
        solve_knapsack(dictionary["values"], dictionary["weights"], dictionary["knapsack_weight"])
        end_time = time.time()
        execution_timeof_dataset.append(end_time - start_time)
        print(len(dictionary["values"]))

        dictionary["values"].clear()
        dictionary["weights"].clear()
        dictionary["knapsack_weight"] = 0

    plt.plot(dataset_length, execution_timeof_dataset, marker='o')
    plt.xlabel('Input Size')
    plt.ylabel('Execution Time')
    plt.title('Knapsack Algorithm Time Complexity in BruteForce Approach')
    plt.show()


def main():
    # In python the max recursion limit is 1000 and the hardness level of 3 exceeds it.
    # sys.setrecursionlimit(2 ^ 100)  # but It couldn't solve it still

    draw_timecomplexity_plot("bruteforce_dataset.txt", 20)


main()

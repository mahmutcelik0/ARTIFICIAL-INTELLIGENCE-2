import os.path
import time
import matplotlib
matplotlib.use('TkAgg')
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


def solveKnapsackDynamicProgramming(capacity, weight, values, n):
    # Build table 2D Matrix
    # Rows are maximum item number, columns are maximum capacity.
    K = [[0 for x in range(capacity + 1)] for x in range(n + 1)]

    # Build table K[][] in bottom up manner
    for i in range(n + 1):
        for w in range(capacity + 1):
            if i == 0 or w == 0:
                K[i][w] = 0
            elif weight[i - 1] <= w:
                K[i][w] = max(values[i - 1] + K[i - 1][w - weight[i - 1]], K[i - 1][w])
            else:
                K[i][w] = K[i - 1][w]

    return K[n][capacity]

def draw_timecomplexity_plot(filename, number):
    execution_timeof_dataset = []
    dataset_length = []

    for x in range(number + 1):
        start_time = time.time()
        fill_the_dictionary(filename, x)
        print(dictionary)
        solveKnapsackDynamicProgramming(dictionary["knapsack_weight"], dictionary["weights"], dictionary["values"],  len(dictionary["values"]))
        end_time = time.time()
        execution_timeof_dataset.append(end_time - start_time)
        dataset_length.append(len(dictionary["values"]))
        print(len(dictionary["values"]))

        dictionary["values"].clear()
        dictionary["weights"].clear()
        dictionary["knapsack_weight"] = 0

    plt.plot(dataset_length, execution_timeof_dataset, marker='o')
    plt.xlabel('Input Size')
    plt.ylabel('Execution Time')
    plt.title('{} Different Data Set\nKnapsack Algorithm Time Complexity in Dynamic Programming Approach'.format(number+1))
    plt.show()


def main():
    draw_timecomplexity_plot("dynamic_dataset.txt", 9)


main()
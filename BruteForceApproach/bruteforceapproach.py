import os.path
import time

import matplotlib.pyplot as plt

# Veri seti saklanır
dictionary = {
    "values": [],
    "weights": [],
    "knapsack_weight": 0,
    "path": "",
}


# Veri setini verilen dosya adından ve hangi veri setinin istendiği kullanarak doldurulur
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


# Recursive solution u çağırır
def solve_knapsack(profits, weights, capacity):
    return knapsack_recursive(profits, weights, capacity, 0)


def knapsack_recursive(values, weights, capacity, currentIndex):
    # Base condition
    if capacity <= 0 or currentIndex >= len(values):
        return 0

    # currentIndex'te öğeyi seçtikten sonra recursive call
    # currentIndex'teki nesnenin weighti çanta ağırlığını aşarsa, bunu çalıştırmayız
    profit1 = 0
    if weights[currentIndex] <= capacity:
        profit1 = values[currentIndex] + knapsack_recursive(
            values, weights, capacity - weights[currentIndex], currentIndex + 1)

    # currentIndex'teki öğeyi hariç tuttuktan sonra recursive call
    profit2 = knapsack_recursive(values, weights, capacity, currentIndex + 1)

    # İki sonuçtan max olanı return eder
    return max(profit1, profit2)


# Number kadar döner yani 20 tane veri seti varsa 20 sini çözer saklar ve en sonda çizer
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

    draw_timecomplexity_plot("bruteforce_dataset.txt", 15)  # Mevcut veri setinde çözüm biraz uzun sürüyor


main()

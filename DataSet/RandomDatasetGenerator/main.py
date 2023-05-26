import random
import os

values = {
    "hardness_level": 1,
    "min_value_of_weight": 1,
    "max_value_of_weight": 20,
    "min_value_of_knapsack_weight": 10,
    "max_value_of_knapsack_weight": 1000,
    "dataset_length": 0,
    "path_of_folder": os.path.join("..", "1-Easy"),
    "weight_file": "weight_file.txt",
    "value_file": "value_file.txt",
    "knapsack_file": "knapsack_file.txt"
}


def set_level_of_hardness():
    match values["hardness_level"]:
        case 1:
            values["dataset_length"] = 15
            values["path_of_folder"] = os.path.join("..", "1-Easy")
        case 2:
            values["dataset_length"] = 100
            values["path_of_folder"] = os.path.join("..", "2-Medium")
        case 3:
            values["dataset_length"] = 1000
            values["path_of_folder"] = os.path.join("..", "3-Hard")


def generate_random_number(start, end):
    return random.randint(start, end)


def file_in_path(file):
    new_file = os.path.join(values["path_of_folder"], file)
    return new_file


def generate_random_value_and_weight_files():
    # CREATION OF WEIGHTS
    with open(file_in_path(values["weight_file"]), "w") as file:
        for x in range(values["dataset_length"]):
            file.write(str(generate_random_number(values["min_value_of_weight"], values["max_value_of_weight"])) + "\n")

    # CREATION OF VALUES
    with open(file_in_path(values["value_file"]), "w") as file:
        for x in range(values["dataset_length"]):
            file.write(str(generate_random_number(values["min_value_of_weight"], values["max_value_of_weight"])) + "\n")


def generate_random_knapsack_weight_file():
    with open(file_in_path(values["knapsack_file"]), "w") as file:
        file.write(str(generate_random_number(values["min_value_of_knapsack_weight"],
                                              values["max_value_of_knapsack_weight"])) + "\n")


def main():
    set_level_of_hardness()
    generate_random_value_and_weight_files()
    generate_random_knapsack_weight_file()


main()
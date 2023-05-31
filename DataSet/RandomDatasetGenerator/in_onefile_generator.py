import random
import os

values = {
    "min_value_of_weight": 1,
    "max_value_of_weight": 20,
    "min_value_of_knapsack_weight": 10,
    "max_value_of_knapsack_weight": 200,
    "dataset_length": 10,
    "path_of_folder": os.path.join("..", "EXAMPLE_DATASET"),  # CHANGE
    "dataset_file": "dataset_file_2.txt",
    "loop_count": 10
}


def generate_random_number(start, end):
    return random.randint(start, end)


def file_in_path(file):
    new_file = os.path.join(values["path_of_folder"], file)
    return new_file


def generate_random_value_and_weight_files():
    # CREATION OF WEIGHTS
    with open(file_in_path(values["dataset_file"]), "w") as file:
        for a in range(values["loop_count"]):
            for x in range(values["dataset_length"]):
                file.write(
                    str(generate_random_number(values["min_value_of_weight"], values["max_value_of_weight"])) + " ")
            file.write("\n")
            # CREATION OF VALUES
            for x in range(values["dataset_length"]):
                file.write(
                    str(generate_random_number(values["min_value_of_weight"], values["max_value_of_weight"])) + " ")
            file.write("\n")

            file.write(str(generate_random_number(values["min_value_of_knapsack_weight"],
                                                  values["max_value_of_knapsack_weight"])) + "\n")


def main():
    generate_random_value_and_weight_files()


main()

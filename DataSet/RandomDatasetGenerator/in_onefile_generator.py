import random
import os

# Bu aşamada bu yapıyı kullanıyoruz

# Dictionary de sabitler bulunuyor
values = {
    "min_value_of_weight": 1,
    "max_value_of_weight": 20,
    "min_value_of_knapsack_weight": 100,
    "max_value_of_knapsack_weight": 350,
    "dataset_length": 50,
    "path_of_folder": os.path.join("..", "EXAMPLE_DATASET"),  # CHANGE
    "dataset_file": "solve_to_genetic_50.txt",
    "loop_count": 10000
}


# Random integer değer oluşturur
def generate_random_number(start, end):
    return random.randint(start, end)


# İlgili pathten verilen filename ile dosya açılır ve return edilir
def file_in_path(file):
    new_file = os.path.join(values["path_of_folder"], file)
    return new_file


# Tek dosya içerisinde istenen tekrarda veri setleri oluşturur. loop_count 10 ise 10 tane veri seti oluşturur
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

            # values["dataset_length"] = values["dataset_length"] + 1


def main():
    generate_random_value_and_weight_files()


main()

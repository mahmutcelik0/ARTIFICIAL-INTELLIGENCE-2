import random
import os

# DON'T USE THIS CLASS TO GENERATE RANDOM VALUE
# Farklı data seti ihtiyaçlarında kullanacağız

# Sabit değerler dictionary den okunuyor
values = {
    "hardness_level": 4,
    "min_value_of_weight": 1,
    "max_value_of_weight": 20,
    "min_value_of_knapsack_weight": 10,
    "max_value_of_knapsack_weight": 200,
    "dataset_length": 0,
    "path_of_folder": os.path.join("..", "1-Easy"),  # CHANGE
    "weight_file": "weight_file.txt",
    "value_file": "value_file.txt",
    "knapsack_file": "knapsack_file.txt",
    "loop_count": 10000
}


# İstenen düzeye göre oluşturulacak veri zorluğu değişecek
def set_level_of_hardness():
    match values["hardness_level"]:
        case 1:
            values["dataset_length"] = 600
            values["path_of_folder"] = os.path.join("..", "1-Easy")
        case 2:
            values["dataset_length"] = 300
            values["path_of_folder"] = os.path.join("..", "2-Medium")
        case 3:
            values["dataset_length"] = 1000
            values["path_of_folder"] = os.path.join("..", "3-Hard")
        case 4:
            values["dataset_length"] = 50
            values["path_of_folder"] = os.path.join("..", "EXAMPLE_DATASET")


# Random integer değer oluşturur
def generate_random_number(start, end):
    return random.randint(start, end)


# Folder pathten ilgili filename ile file açılır
def file_in_path(file):
    new_file = os.path.join(values["path_of_folder"], file)
    return new_file


# Dataset ne kadar uzun olacaksa o kadar tekrarda random değer oluşturulup dosyaya yazdırılır
def generate_random_value_and_weight_files():
    # CREATION OF WEIGHTS
    with open(file_in_path(values["weight_file"]), "w") as file:
        for x in range(values["dataset_length"]):
            file.write(str(generate_random_number(values["min_value_of_weight"], values["max_value_of_weight"])) + "\n")

    # CREATION OF VALUES
    with open(file_in_path(values["value_file"]), "w") as file:
        for x in range(values["dataset_length"]):
            file.write(str(generate_random_number(values["min_value_of_weight"], values["max_value_of_weight"])) + "\n")


# Random 1 değer oluşturulur ve knapsack ağırlığı olmuş olur
def generate_random_knapsack_weight_file():
    with open(file_in_path(values["knapsack_file"]), "w") as file:
        file.write(str(generate_random_number(values["min_value_of_knapsack_weight"],
                                              values["max_value_of_knapsack_weight"])) + "\n")


# METHOD OVERLOADING
def generate_random_value_and_weight_files_overload(loop_number):
    # CREATION OF WEIGHTS
    with open(file_in_path(values["weight_file"] + "_" + str(loop_number)), "w") as file:
        for x in range(values["dataset_length"]):
            file.write(str(generate_random_number(values["min_value_of_weight"], values["max_value_of_weight"])) + "\n")

    # CREATION OF VALUES
    with open(file_in_path(values["value_file"] + "_" + str(loop_number)), "w") as file:
        for x in range(values["dataset_length"]):
            file.write(str(generate_random_number(values["min_value_of_weight"], values["max_value_of_weight"])) + "\n")


def generate_random_knapsack_weight_file_overload(loop_number):
    with open(file_in_path(values["knapsack_file"] + "_" + str(loop_number)), "w") as file:
        file.write(str(generate_random_number(values["min_value_of_knapsack_weight"],
                                              values["max_value_of_knapsack_weight"])) + "\n")


def main():
    set_level_of_hardness()

    for x in range(values["loop_count"]):
        generate_random_value_and_weight_files_overload(x)
        generate_random_knapsack_weight_file_overload(x)


main()

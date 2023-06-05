import os.path
import time
import matplotlib
import matplotlib.pyplot as plt
matplotlib.use('TkAgg')


# Veri seti saklanır.
dictionary = {
    "values": [],
    "weights": [],
    "knapsack_weight": 0,
    "path": "",
}


# Knapsackteki yapıya benzer okuma ve çizdirme bulunuyor. Fark olarak çözüm algoritması var
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
    # 2 boyutlu array oluşturuluyor. Lamda expression yöntemiyle bu array oluşturulur.
    # Satırlar numarası çantanın alabileceği maksimum item sayısını;
    # Sütunlar çantanın taşıyabileceği maksimum ağırlığı temsil eder.

    K = [[0 for x in range(capacity + 1)] for x in range(n + 1)]


    # İki boyutlu array'de her satırı dolaşarak problemin çözümü bulunmaya çalışılır.
    for i in range(n + 1):
        # İlgili satırdaki her bir sütun için değerler doldurulur.
        # Değerler o andaki maksimum item sayısı (satır no),
        # ve maksimum ağırlık (sütun no) için en çözümü temsil eder.
        for w in range(capacity + 1):
            # İlk satır veya ilk sütunda mutlaka 0 değeri konulur.
            # Çünkü çantanın alabileceği item sayısı 0'dır.
            # Ya da çantanın taşıyabileceği ağırlık 0'dır.
            if i == 0 or w == 0:
                K[i][w] = 0
            # Eğer koyulmak istenen item çantanın o an için taşıyabileceği sınır içindeyse
            # bu koşul çalıştırılır.
            elif weight[i - 1] <= w:
                # O andaki değer şu şekilde belirlenir.

                # O itemin değeri ile O itemin ağırlığını çantanın o andaki ağırlık kapasitesinden çıkarttığımızda
                # oluşan değerin toplamı alınır. Yani itemin değeri ile üst satırda olan, ancak çantanın toplam
                # ağırlık kapasitesinden itemin ağırlığının çıkarıldığı sütundaki değer toplanır.

                # Çantanın taşıyabileceği ağırlık aynı olacak şekilde ama item kapasitesi bir eksik
                # olacak şekildeki değer alınır (Bir üst satırda aynı nokta).

                # Bu iki değerden en büyük olan o noktaya yazılır. Çünkü o durumda en iyi değer bu değerdir.

                K[i][w] = max(values[i - 1] + K[i - 1][w - weight[i - 1]], K[i - 1][w])
            # İtemin ağırlığı kapasiteyi aşıyorsa bir üstteki (aynı kapasitede en iyi değer) değer
            # o noktada yazılır.
            else:
                K[i][w] = K[i - 1][w]
    # Tablonun doldurulmasıyla birlikte, en son doldurulan değer çözüm olur.
    # Yani çözüm bütün itemlerin konulabildiği ve çantanın ilk baştaki ağırlık kapasitesinde olduğu durumdur.
    return K[n][capacity]


# İstenen veri seti kadarı çözdürülüyor ve en sonunda çizim gerçekleşiyor
def draw_timecomplexity_plot(filename, number):
    execution_timeof_dataset = []
    dataset_length = []

    for x in range(number + 1):
        start_time = time.time()
        fill_the_dictionary(filename, x)
        print(dictionary)
        solveKnapsackDynamicProgramming(dictionary["knapsack_weight"], dictionary["weights"], dictionary["values"],
                                        len(dictionary["values"]))
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
    plt.title(
        '{} Different Data Set\nKnapsack Algorithm Time Complexity in Dynamic Programming Approach'.format(number + 1))
    plt.show()


def main():
    draw_timecomplexity_plot("dynamic_dataset.txt", 9)


main()

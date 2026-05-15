
name = "Hillary"
print(f"Hello {name}!")






# CMP 269: Python Introduction Exercises

def exercise_1_basics():
    course = "CMP 269"
    students = 60
    print(f"The course {course} has {students} students.")


def exercise_2_collections():
    # 1. Create a list of 5 colors
    colors = ["red", "blue", "green", "yellow", "purple"]

    # 2. Add a 6th color
    colors.append("orange")

    print("Colors list:", colors)

    # 3. Create a dictionary
    student = {
        "name": "Hillary",
        "gpa": 3.5
    }

    print("Student dictionary:", student)


def exercise_3_logic():
    numbers = [1, 2, 3, 4, 5, 6, 7, 8]
    evens = []

    for num in numbers:
        if num % 2 == 0:
            evens.append(num)

    print("Even numbers:", evens)


if __name__ == "__main__":
    print("--- Exercise 1 ---")
    exercise_1_basics()

    print("\n--- Exercise 2 ---")
    exercise_2_collections()

    print("\n--- Exercise 3 ---")
    exercise_3_logic()
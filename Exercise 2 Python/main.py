# CMP 269: Programming Methods III
# Exercise 2 - My Version

class Student:
    def __init__(self, name, gpa):
        self.name = name
        self.gpa = gpa
        self._is_active = True

    def get_status(self):
        status = "Active" if self._is_active else "Inactive"
        return f"{self.name} is currently {status} with a {self.gpa} GPA."


class GraduateStudent(Student):
    def __init__(self, name, gpa, major):
        super().__init__(name, gpa)
        self.major = major

    def get_status(self):
        base_status = super().get_status()
        return f"{base_status} They are studying {self.major}."


class Club:
    def get_status(self):
        return "The programming club is currently active."


def exercise_3_polymorphism():
    student1 = Student("Hillary Cabrera", 3.5)
    student2 = GraduateStudent("Ana Rivera", 3.8, "Software Engineering")
    club = Club()

    items = [student1, student2, club]

    for item in items:
        print(item.get_status())


if __name__ == "__main__":
    print("--- Exercise 2: Classes and Inheritance ---")
    exercise_3_polymorphism()
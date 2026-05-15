import requests
import json
import os

def task_1_append_logger():
    print("--- Task 1: Append Logger ---")
    note = input("Enter a note for the log: ")

    with open("session_log.txt", "a") as file:
        file.write(note + "\n")

    with open("session_log.txt", "r") as file:
        print(file.read())


def task_2_word_count_utility():
    print("\n--- Task 2: Word Count Utility ---")

    text = "Knowledge is Power. Go Lightning! Python makes data easy."

    with open("lehman_motto.txt", "w") as file:
        file.write(text)

    with open("lehman_motto.txt", "r") as file:
        content = file.read()

    words = content.split()
    print("Word count:", len(words))


def task_3_api_status_checker():
    print("\n--- Task 3: API Status Checker ---")

    try:
        response = requests.get("https://jsonplaceholder.typicode.com/posts/101", timeout=5)

        if response.status_code == 200:
            print(response.json())
        elif response.status_code == 404:
            print("Error: Post not found.")
        else:
            print("Status code:", response.status_code)

    except requests.exceptions.Timeout:
        print("Error: Request timed out.")
    except requests.exceptions.RequestException as e:
        print("Network error:", e)


def task_4_data_filtering():
    print("\n--- Task 4: Data Filtering ---")

    response = requests.get("https://jsonplaceholder.typicode.com/users")
    users = response.json()

    for user in users:
        if "Suite" in user["address"]["suite"]:
            print(user["name"])


def task_5_integration_report():
    print("\n--- Task 5: Integration Report ---")

    response = requests.get("https://jsonplaceholder.typicode.com/posts/1")
    data = response.json()

    title = data["title"]
    body = data["body"]

    with open("api_report.txt", "w") as file:
        file.write("Title: " + title + "\n")
        file.write("Body: " + body + "\n")

    print("Report Generated")


if __name__ == "__main__":
    task_1_append_logger()
    task_2_word_count_utility()
    task_3_api_status_checker()
    task_4_data_filtering()
    task_5_integration_report()
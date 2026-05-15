#Exercise 6
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns


def get_crypto_data():
    """Helper function to load mock crypto data."""
    return pd.DataFrame({
        "Day": [1, 2, 3, 4, 5, 6, 7],
        "Bitcoin": [40000, 42000, 41000, 45000, 44000, 46000, 48000],
        "Ethereum": [2500, 2600, 2550, 2800, 2750, 2900, 3100]
    })
#Task 1
def task_1_trend_line():
    """
    TASK 1: Matplotlib Line Chart
    1. Load the data using get_crypto_data().
    2. Use plt.plot() to chart Bitcoin prices over the 7 days.
    3. Add a title, x-axis label, and y-axis label.
    4. Call plt.show() to render it.
    """

    print("--- Task 1: Building a Trend Line ---")
    df = get_crypto_data()

    plt.plot(df["Day"], df["Bitcoin"])
    plt.title("Bitcoin Prices Over 7 Days")
    plt.xlabel("Day")
    plt.ylabel("Bitcoin Price")
    plt.show()

#Task 2
def task_2_seaborn_comparison():
    """
    TASK 2: Seaborn Bar Chart
    """

    print("--- Task 2: Seaborn Comparison ---")

    portfolio_data = pd.DataFrame({
        "Portfolio": ["Portfolio A", "Portfolio B", "Portfolio C"],
        "Value": [10000, 15000, 8000]
    })

    sns.barplot(x="Portfolio", y="Value", data=portfolio_data)

    plt.title("Portfolio Comparison")
    plt.xlabel("Portfolio")
    plt.ylabel("Total Value")
    plt.show()

if __name__ == "__main__":
    task_1_trend_line()
    task_2_seaborn_comparison()
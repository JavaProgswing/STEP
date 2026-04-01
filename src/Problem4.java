class Asset {
    double returnRate;
    double volatility;

    Asset(double r, double v) {
        returnRate = r;
        volatility = v;
    }
}

public class Problem4 {

    static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (
                arr[j].returnRate > pivot.returnRate ||
                (arr[j].returnRate == pivot.returnRate &&
                 arr[j].volatility < pivot.volatility)
            ) {
                i++;
                Asset temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Asset temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    public static void main(String[] args) {
        Asset[] arr = {
            new Asset(12, 5),
            new Asset(8, 7),
            new Asset(15, 6)
        };

        quickSort(arr, 0, arr.length - 1);
    }
}

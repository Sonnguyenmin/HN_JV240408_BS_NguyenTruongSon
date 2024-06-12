package ra.run;

import ra.bussiness.Book;

import java.util.Scanner;

public class BookManagement {
    public static Book[] arrBook = new Book[100];
    public static int indexBook = 0;

//    static {
//        Book book = new Book(1,"thien phong","thanh nine","bao thanh nien truyen thong", 12000.0, 18000.0, 6000, true);
//        Book book2 = new Book(2,"bao noi tam","noi tam","nha xuat ban thanh pho ho chi minh", 15000.0, 20000.0, 5000, true);
//        Book book3 = new Book(3,"ngoai giao","noi tam","Kim dong ha noi", 18000.0, 26000.0, 8000, true);
//        arrBook[0]=book;
//        arrBook[1]=book2;
//        arrBook[2]=book3;
//        indexBook=3;
//    }

    public static void main(String[] args) {

        //Menu sach
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*****************      AVAVAVA-HACKATATHON-05-BASIC-MENU       ******************");
            System.out.println("* 1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách           *");
            System.out.println("* 2. Hiển thị thông tin tất cả sách trong thư viện                              *");
            System.out.println("* 3. Sắp xếp sách theo lợi nhuận tăng dần                                       *");
            System.out.println("* 4. Xóa sách theo mã sách                                                      *");
            System.out.println("* 5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả                           *");
            System.out.println("* 6. Thay đổi thông tin sách theo mã sách                                       *");
            System.out.println("* 7. Thoát                                                                      *");
            System.out.println("*********************************************************************************");
            System.out.println("Lựa chọn chức năng : ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    BookManagement.inputListBook(scanner);
                    break;
                case 2:
                    BookManagement.displayListBook();
                    break;
                case 3:
                    BookManagement.sortAscendingInterest();
                    break;
                case 4:
                    BookManagement.deleteBook(scanner);
                    break;
                case 5:
                    BookManagement.searchBookByName(scanner);
                    break;
                case 6:
                    BookManagement.updateBook(scanner);
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Vui lòng nhập tu 1 - 8");
            }
        } while (true);
    }
    //Case 1: Nhap thong tin cua sach
    public static void inputListBook(Scanner scanner) {
        System.out.println("Mời nhập vào số sách cần nhập: ");
        int countBook = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < countBook; i++) {
            arrBook[indexBook] = new Book();
            arrBook[indexBook].inputData(scanner);
            indexBook++;
        }
    }

    //Case 2: Hien thi thong tin sach
    public static void displayListBook() {
        for (int i = 0; i < indexBook; i++) {
            arrBook[i].displayData();
        }
    }
    //Case3: Sắp xếp sách theo lợi nhuận tăng dần
    public static void sortAscendingInterest() {
        for (int i = 0; i < indexBook - 1; i++) {
            if (arrBook[i] != null) {
                int minIndex = i;
                for (int j = i + 1; j < indexBook; j++) {
                    if (arrBook[j] != null && arrBook[j].getInterest() < arrBook[minIndex].getInterest()){
                        minIndex = j;
                    }
                }
                Book temp = arrBook[i];
                arrBook[i]=arrBook[minIndex];
                arrBook[minIndex] = temp;
            }
        }
        System.out.println("Đã sắp xếp xong sách theo lợi nhuận tăng dần");
    }

    //Lay vi tri id
    public static int getIndexById(int bookId) {
        for (int i = 0; i < indexBook; i++) {
            if (arrBook[i].getBookId() == bookId) {
                return i;
            }
        }
        return -1;
    }

    //Case 4: Xóa sách theo mã sách
    public static void deleteBook (Scanner scanner) {
        System.out.println("Mời nhập vào sách cần xóa: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        int indexDelete = getIndexById(bookId);
        if(indexDelete >= 0) {
            for (int i = indexDelete; i < indexBook; i++) {
                arrBook[i] = arrBook[i + 1];
            }
            indexBook--;
            System.out.printf("Đã xóa mã sách %d\n", indexDelete+1);
        }
        else {
            System.err.println("Mã sách không tồn tại");
        }
    }

    //Case 5: Tìm kiếm tương đối sách theo tên sách hoặc mô tả
    public static void searchBookByName(Scanner scanner) {
        System.out.println("Nhập vào tên hoặc mô tả sách cần tìm kiếm: ");
        String bookNameSearch = scanner.nextLine();
        int countBook = 0;
        for (int i = 0; i < indexBook; i++) {
            if(arrBook[i].getBookName().toLowerCase().contains(bookNameSearch.toLowerCase())
            || arrBook[i].getDescription().toLowerCase().contains(bookNameSearch.toLowerCase()) ) {
                arrBook[i].displayData();
                countBook++;
            }
        }
        System.out.printf("Có %d số sách được tìm thấy \n", countBook);
    }

    //Case 6: Thay đổi thông tin sách theo mã sách
    public static void updateBook(Scanner scanner) {
        System.out.println("Nhập mã sách muốn thay đổi thông tin: ");
        int updateId = Integer.parseInt(scanner.nextLine());
        boolean found = false;
        for (int i = 0; i < indexBook; i++) {
            if (arrBook[i].getBookId() == updateId) {
                System.out.println("Nhập thông tin mới cho sách: ");
                arrBook[i].updateData(scanner);
                found = true;
            }
        }
        if (!found) {
            System.err.println("Không tìm thấy sách với mã sách đã nhập.");
        }
    }
}

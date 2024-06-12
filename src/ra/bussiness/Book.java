package ra.bussiness;

import ra.run.BookManagement;

import java.util.Scanner;

public class Book {
    //Khoi tao cac thuoc tinh
    private int bookId;
    private String bookName;
    private String author;
    public String description;
    public double importPrice;
    private double exportPrice;
    private float interest;
    private boolean bookStatus;

    //Goi cac phuong thuc
    //Constructor Khong tham so
    public Book() {
    }
    //Constructor co tham so
    public Book(int bookId, String bookName, String author, String description, double importPrice, double exportPrice, float interest, boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.bookStatus = bookStatus;
    }
    //Cac phuong thuc getter/setter

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }
    //Khoi tao phuong thuc inputData Cho phep nhap vao du lieu
    public void inputData(Scanner scanner) {
        //▪ bookId – mã sách – int (Tự động tăng)
        this.bookId = inputBookId();
        //▪ bookName – tên sách – String (Không được để trống)
        this.bookName = inputBookName(scanner);
        //▪ author – tác giả – String (Không được để trống)
        this.author = inputAuthor(scanner);
        //▪ descriptions – mô tả về sách – String (Không được để trống, ít nhất 10 ký tự)
        this.description = inputDescription(scanner);
        //▪ importPrice – giá nhập – double (phải lớn hơn 0)
        this.importPrice = inputImportPrice(scanner);
        //▪ exportPrice – giá xuất – double (phải lớn hơn 1.2 lần giá nhập)
        this.exportPrice = inputExportPrice(scanner);
        //▪ interest – lợi nhuận – float
        this.interest = inputInterest(importPrice, exportPrice);
        //▪ bookStatus – trạng thái – Boolean (mặc định là true)
        this.bookStatus = inputBookStatus(scanner);
    }
    //Khoi tao phuong thuc updateData Cho phep thay doi du lieu
    public void updateData(Scanner scanner) {
        this.bookName = inputBookName(scanner);
        this.author = inputAuthor(scanner);
        this.description = inputDescription(scanner);
        this.importPrice = inputImportPrice(scanner);
        this.exportPrice = inputExportPrice(scanner);
        this.interest = inputInterest(importPrice, exportPrice);
        this.bookStatus = inputBookStatus(scanner);
    }

    //Nhap vao ma sach tu tang ▪ bookId – mã sách – int (Tự động tăng)
    public int inputBookId() {
        if (BookManagement.indexBook == 0) {
            return 1;
        } else {
            int maxId = BookManagement.arrBook[0].getBookId();
            for (int i = 0; i < BookManagement.indexBook; i++) {
                if (maxId < BookManagement.arrBook[i].getBookId()) {
                    maxId = BookManagement.arrBook[i].getBookId();
                }
            }
            return maxId + 1;
        }
    }
    //Nhap vao ten sach ▪ bookName – tên sách – String (Không được để trống)
    public String inputBookName(Scanner scanner) {
        System.out.println("Mời nhập vào tên sách: ");
        do {
            String bookName = scanner.nextLine();
            if(bookName.trim().isEmpty()) {
                System.err.println("* Tên sách không được để trống");
            } else {
                if(bookName.length() >= 4 && bookName.length() <= 50) {
                    return bookName;
                }
                else {
                    System.err.println("Tên sách phai nhập từ 4 đến 50 ký tự");
                }
            }
        } while (true);
    }
    //Nhap vao tác giả ▪ author – tác giả – String (Không được để trống)
    public String inputAuthor(Scanner scanner) {
        System.out.println("Mời nhập vào tên tác giả: ");
        do {
            String author = scanner.nextLine();
            if(author.trim().isEmpty()) {
                System.err.println("* Tên tác giả không được để trống");
            } else {
                return author;
            }
        } while (true);
    }

    //Nhập vao mô tả ▪ descriptions – mô tả về sách – String (Không được để trống, ít nhất 10 ký tự)
    public String inputDescription(Scanner scanner) {
        System.out.println("Mời nhập vào mô tả sách: ");
        do {
            String description = scanner.nextLine();
            if(description.trim().isEmpty()) {
                System.err.println("* Mô tả sách không được để trống");
            } else {
                if(description.length() >= 10) {
                    return description;
                }
                else {
                    System.err.println("Mô tả sách phải nhập ít nhất 10 ký tự");
                }
            }
        } while (true);
    }
    // Nhap vao gia nhap ▪ importPrice – giá nhập – double (phải lớn hơn 0)
    public double inputImportPrice (Scanner scanner) {
        System.out.println("Mời nhập vào giá nhập của sách:");
        do{
            double importPrice = Double.parseDouble(scanner.nextLine());
            if (importPrice > 0) {
                return importPrice;
            } else {
                System.err.println("Giá nhập của sách có giá trị lớn hơn 0,vui lòng nhập lại");
            }
        } while (true);
    }

    //Nhap vao gia xuat ▪ exportPrice – giá xuất – double (phải lớn hơn 1.2 lần giá nhập)
    public double inputExportPrice(Scanner scanner) {
        System.out.println("Mời nhập vào giá xuất của sách");
        do {
            double exportPrice = Double.parseDouble(scanner.nextLine());
            if (exportPrice >= importPrice * 1.2) {
                return exportPrice;
            } else {
                System.err.println("Giá xuất của sách có giá trị lớn hơn ít nhất 20% so với giá nhập, vui lòng nhập lại");
            }
        } while (true);
    }

    //loi nhuan ▪ interest – lợi nhuận – float

    public float inputInterest(double importPrice, double exportPrice) {
        return (float) (exportPrice - importPrice);
    }

    //Nhap vao trang thai sach ▪ bookStatus – trạng thái – Boolean (mặc định là true)
    public boolean inputBookStatus(Scanner scanner) {
        System.out.println("Mời nhập vào trang thái sách");
        do {
            String status = scanner.nextLine();
            if( status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            } else  {
                System.err.println("Trạng thái sách chỉ nhận giá trị true | false, vui lòng nhập lại");
            }
        } while (true);
    }
    //Khoi tao phuong thuc displayData Cho phep hien thi thong tin sach
    public void displayData() {
        System.out.printf("Ma sach: %s - Ten sach: %s - Gia tien nhap: %s - Gia tien ban: %s \n", this.bookId, this.bookName, this.importPrice, this.exportPrice);
        System.out.printf("Ten tac gia: %s - Loi nhuan: %s - Trang thai: %s \n", this.author, this.interest, this.bookStatus ? "Đang bán" : "Không bán");
        System.out.printf("Mo ta: %s \n", this.description);
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }
}

package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SinhVien {
  String MSSV;
  String hoTen;
  double diemTB;

  public SinhVien(String MSSV, String hoTen, double diemTB) {
    this.MSSV = MSSV;
    this.hoTen = hoTen;
    this.diemTB = diemTB;
  }
}

package models;

public class StudentModel {
  private String MSSV;
  private String hoTen;
  private boolean gioiTinh;
  private int namSinh;

  public StudentModel(String MSSV, String hoTen, boolean gioiTinh, int namSinh) {
    this.MSSV = MSSV;
    this.hoTen = hoTen;
    this.gioiTinh = gioiTinh;
    this.namSinh = namSinh;
  }

  public String getMSSV() {
    return MSSV;
  }

  public String getHoTen() {
    return hoTen;
  }

  public boolean isGioiTinh() {
    return gioiTinh;
  }

  public int getNamSinh() {
    return namSinh;
  }
}

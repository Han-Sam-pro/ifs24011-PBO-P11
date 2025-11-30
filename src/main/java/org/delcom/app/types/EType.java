package org.delcom.app.types;

public enum EType {
    PEMASUKAN,    // Pastikan ini ada (bukan INCOME)
    PENGELUARAN;  // Pastikan ini ada (bukan EXPENSE)

    // Method ini HARUS ada agar error 'undefined' hilang
    public static EType fromString(String text) {
        if (text == null) {
            return null;
        }
        // Membersihkan spasi dan cek case-insensitive
        for (EType e : EType.values()) {
            if (e.name().equalsIgnoreCase(text.trim())) {
                return e;
            }
        }
        return null;
    }
}
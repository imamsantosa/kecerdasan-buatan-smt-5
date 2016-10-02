/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgenCerdasModif;

/**
 *
 * @author imamsantosa
 */
public class ModelBased {
    private String lingkungan[][];
    
    ModelBased(int location){
        lingkungan = new String[location][4];
    }
    public void setKondisi(int i, String Nama,String location,String status,String kotoran){
        lingkungan[i-1][0]=Nama;
        lingkungan[i-1][1]=location;
        lingkungan[i-1][2]=status;
        lingkungan[i-1][3]=kotoran;
    }
    
    public void vacumm (int i,String location, String status, String kotoran){
        if ("kotor".equals(status.toLowerCase())){
            if("debu".equals(kotoran.toLowerCase())){
                System.out.println("--- Debu Terditeksi ---");
                System.out.println("----bersih-bersih----");
            }
            status = "bersih";
            lingkungan[i][2]=status;
            vacumm(i,location,status, kotoran);
        }else if ("kiri".equals(location.toLowerCase())){
            System.out.println("geser kanan-->");
        }else if ("kanan".equals(location.toLowerCase())){
            System.out.println("<--geser kiri");
        }
    }
    public void bersihkan(){
        for (int i = 0; i<lingkungan.length; i++){
            System.out.println("Lokasi"+lingkungan[i][0]);
            vacumm(i,lingkungan[i][1],lingkungan[i][2], lingkungan[i][3]);
        }
        cek(); 
    }
    public void cek (){
        for (int i = 0; i<lingkungan.length; i++){
            if(!"bersih".equals(lingkungan[i][2])){
                bersihkan();
                break;
            }
        }
        System.out.println("SEMUA SUDAH BERSIH");
    }
    public static void main(String[] args) {
        ModelBased a = new ModelBased(6);
        a.setKondisi(1, "Lokasi 1", "kanan", "bersih", "");
        a.setKondisi(2, "Lokasi 2", "kiri", "kotor", "debu");
        a.setKondisi(3, "Lokasi 3", "kanan", "kotor", "kaca");
        a.setKondisi(4, "Lokasi 4", "kiri", "kotor", "plastik");
        a.setKondisi(5, "Lokasi 5", "kanan", "kotor", "debu");
        a.setKondisi(6, "Lokasi 6", "kiri", "bersih", "");
        a.bersihkan();
    }
}

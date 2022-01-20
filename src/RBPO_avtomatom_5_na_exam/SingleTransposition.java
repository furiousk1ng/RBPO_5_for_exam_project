package RBPO_avtomatom_5_na_exam;

public class SingleTransposition {

    public boolean lengthCheck(String str1, String str2){
        return (str1.length()==str2.length());
    }
    
    public boolean letterMatch(String str1, String str2) {
        int sayac=0;
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str1.length(); j++) {
                if(str1.substring(i,i+1).equals(str2.substring(j, j+1))) {
                    sayac++;
                    break;
                }
            }
        }
        return str1.length()==sayac;
    }

    public boolean findSimilar(String str1, String str2){
        if(lengthCheck(str1, str2) && letterMatch(str1, str2) ){
            for (int i = 0; i < str1.length()-1; i++) {
                if(!str1.substring(i,i+1).equals(str2.substring(i,i+1))) {
                    if(str1.substring(i, i+1).equals(str2.substring(i+1, i+2)) &&
                            str2.substring(i, i+1).equals(str1.substring(i+1,i+2))) {
                        if(str1.substring(i+2).equals(str2.substring(i+2))){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
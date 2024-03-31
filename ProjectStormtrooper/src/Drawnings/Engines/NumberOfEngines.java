package Drawnings.Engines;

public enum NumberOfEngines {
    TWO(2),
    FOUR(4),
    SIX(6);

    private final int value;
    NumberOfEngines(int value){
        this.value=value;
    }
    public static NumberOfEngines getNumber(int amount){
        NumberOfEngines [] num = NumberOfEngines.values();
        for(int i =0 ; i <num.length;i++){
            if(amount==num[i].value){
                return num[i];
            }
        }
        return null;
    }
    public static boolean contains(int amount){
        NumberOfEngines [] num = NumberOfEngines.values();
        for(int i =0 ; i <num.length;i++){
            if(amount==num[i].value){
                return  true;
            }
        }
        return  false;
    }
}

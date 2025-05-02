package model;

import java.util.Arrays;
import java.util.List;


public class UnidadeCondensadoras {
    private String marca;
    private String modelo;
    private int HP;
    private double capacidadeKcal; // Capacidade em kcal/h
    private double temperaturaMin; // Temperatura mínima de operação (°C)
    private double temperaturaMax; // Temperatura máxima de operação (°C)
    private String gas;
    private String tensao;
    private double Preco;

    public UnidadeCondensadoras(String marca, String modelo,int HP, double capacidadeKcal, double temperaturaMin, double temperaturaMax,String gas, String tensao, double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.HP = HP;
        this.capacidadeKcal = capacidadeKcal;
        this.temperaturaMin = temperaturaMin;
        this.temperaturaMax = temperaturaMax;
        this.gas = gas;
        this.tensao = tensao;
        this.Preco = preco;
    }

    public String getModelo() { return modelo; }
    public int getHP() {return HP;}
    public double getCapacidadeKcal() { return capacidadeKcal; }
    public double getTemperaturaMin() { return temperaturaMin; }
    public double getTemperaturaMax() { return temperaturaMax; }
    public String getTensao() { return tensao; }
    public double getPreco() { return Preco; }

    private static final List<UnidadeCondensadoras> catalogo = Arrays.asList(
            new UnidadeCondensadoras("Danfoss","OP-HJM019",15,2618,-5,20,"R22","220V",6000),
            new UnidadeCondensadoras("Danfoss","OP-HJM019",15,3324,0,20,"R22","220V",6000),
            new UnidadeCondensadoras("Danfoss","OP-HJM019",15,4130,5,20,"R22","220V",6000),
            new UnidadeCondensadoras("Danfoss","OP-HJM019",15,5034,10,20,"R22","220V",6000),

            new UnidadeCondensadoras("Danfoss","OP-HJM022",20,3586,-5,20,"R22","220V",6500),
            new UnidadeCondensadoras("Danfoss","OP-HJM022",20,4421,0,20,"R22","220V",6500),
            new UnidadeCondensadoras("Danfoss","OP-HJM022",20,5334,5,20,"R22","220V",6500),
            new UnidadeCondensadoras("Danfoss","OP-HJM022",20,6321,10,20,"R22","220V",6500),

            new UnidadeCondensadoras("Danfoss","OP-HJM028",250,5279,-5,20,"R22","220V",7000),
            new UnidadeCondensadoras("Danfoss","OP-HJM028",250,6395,0,20,"R22","220V",7000),
            new UnidadeCondensadoras("Danfoss","OP-HJM028",250,7616,5,20,"R22","220V",7000),
            new UnidadeCondensadoras("Danfoss","OP-HJM028",250,8943,10,20,"R22","220V",7000),

            new UnidadeCondensadoras("Danfoss","OP-HJM032",300,5584,-5,20,"R22","220V",7500),
            new UnidadeCondensadoras("Danfoss","OP-HJM032",300,6535,0,20,"R22","220V",7500),
            new UnidadeCondensadoras("Danfoss","OP-HJM032",300,7850,5,20,"R22","220V",7500),
            new UnidadeCondensadoras("Danfoss","OP-HJM032",300,9291,10,20,"R22","220V",7500),

            new UnidadeCondensadoras("Danfoss","OP-HJM036",350,6508,-5,20,"R22","220V",7600),
            new UnidadeCondensadoras("Danfoss","OP-HJM036",350,7796,0,20,"R22","220V",7600),
            new UnidadeCondensadoras("Danfoss","OP-HJM036",350,9185,5,20,"R22","220V",7600),
            new UnidadeCondensadoras("Danfoss","OP-HJM036",350,10670,10,20,"R22","220V",7600),

            new UnidadeCondensadoras("Danfoss","OP-HJM040",400,7421,-5,20,"R22","220V",7700),
            new UnidadeCondensadoras("Danfoss","OP-HJM040",400,8967,0,20,"R22","220V",7700),
            new UnidadeCondensadoras("Danfoss","OP-HJM040",400,10670,5,20,"R22","220V",7700),
            new UnidadeCondensadoras("Danfoss","OP-HJM040",400,12210,10,20,"R22","220V",7700),

            new UnidadeCondensadoras("Danfoss","OP-HJM044",450,7555,-5,20,"R22","220V",8000),
            new UnidadeCondensadoras("Danfoss","OP-HJM044",450,9348,0,20,"R22","220V",8000),
            new UnidadeCondensadoras("Danfoss","OP-HJM044",450,11330,5,20,"R22","220V",8000),
            new UnidadeCondensadoras("Danfoss","OP-HJM044",450,13510,10,20,"R22","220V",8000),

            new UnidadeCondensadoras("Danfoss","OP-HJM050",500,8541,-5,20,"R22","220V",8500),
            new UnidadeCondensadoras("Danfoss","OP-HJM050",500,10460,0,20,"R22","220V",8500),
            new UnidadeCondensadoras("Danfoss","OP-HJM050",500,12590,5,20,"R22","220V",8500),
            new UnidadeCondensadoras("Danfoss","OP-HJM050",500,14910,10,20,"R22","220V",8500),

            new UnidadeCondensadoras("Danfoss","OP-HJM056",550,9554,-5,20,"R22","220V",9000),
            new UnidadeCondensadoras("Danfoss","OP-HJM056",550,11620,0,20,"R22","220V",9000),
            new UnidadeCondensadoras("Danfoss","OP-HJM056",550,13880,5,20,"R22","220V",9000),
            new UnidadeCondensadoras("Danfoss","OP-HJM056",550,16320,10,20,"R22","220V",9000),

            new UnidadeCondensadoras("Danfoss","OP-HJM064",650,10580,-5,20,"R22","220V",9500),
            new UnidadeCondensadoras("Danfoss","OP-HJM064",650,12860,0,20,"R22","220V",9500),
            new UnidadeCondensadoras("Danfoss","OP-HJM064",650,15360,5,20,"R22","220V",9500),
            new UnidadeCondensadoras("Danfoss","OP-HJM064",650,18080,10,20,"R22","220V",9500),

            new UnidadeCondensadoras("Danfoss","OP-HJM072",700,12600,-5,20,"R22","220V",10000),
            new UnidadeCondensadoras("Danfoss","OP-HJM072",700,15370,0,20,"R22","220V",10000),
            new UnidadeCondensadoras("Danfoss","OP-HJM072",700,18460,5,20,"R22","220V",10000),
            new UnidadeCondensadoras("Danfoss","OP-HJM072",700,21840,10,20,"R22","220V",10000),

            new UnidadeCondensadoras("Danfoss","OP-HJM080",800,14110,-5,20,"R22","220V",10500),
            new UnidadeCondensadoras("Danfoss","OP-HJM080",800,17120,0,20,"R22","220V",10500),
            new UnidadeCondensadoras("Danfoss","OP-HJM080",800,20440,5,20,"R22","220V",10500),
            new UnidadeCondensadoras("Danfoss","OP-HJM080",800,24040,10,20,"R22","220V",10500),

            new UnidadeCondensadoras("Danfoss","OP-HJM0100",900,15480,-5,20,"R22","220V",11000),
            new UnidadeCondensadoras("Danfoss","OP-HJM0100",900,18850,0,20,"R22","220V",11000),
            new UnidadeCondensadoras("Danfoss","OP-HJM0100",900,22580,5,20,"R22","220V",11000),
            new UnidadeCondensadoras("Danfoss","OP-HJM0100",900,26660,10,20,"R22","220V",11000),

            new UnidadeCondensadoras("Danfoss","OP-HJM0125",1000,21320,-5,20,"R22","220V",12000),
            new UnidadeCondensadoras("Danfoss","OP-HJM0125",1000,25910,0,20,"R22","220V",12000),
            new UnidadeCondensadoras("Danfoss","OP-HJM0125",1000,30980,5,20,"R22","220V",12000),
            new UnidadeCondensadoras("Danfoss","OP-HJM0125",1000,36540,10,20,"R22","220V",12000),

            new UnidadeCondensadoras("Danfoss","OP-HJM0144",1100,23680,-5,20,"R22","220V",13000),
            new UnidadeCondensadoras("Danfoss","OP-HJM0144",1100,28670,0,20,"R22","220V",13000),
            new UnidadeCondensadoras("Danfoss","OP-HJM0144",1100,34180,5,20,"R22","220V",13000),
            new UnidadeCondensadoras("Danfoss","OP-HJM0144",1100,40180,10,20,"R22","220V",13000),

            new UnidadeCondensadoras("Danfoss","OP-HJM0160",1200,25740,-5,20,"R22","220V",15000),
            new UnidadeCondensadoras("Danfoss","OP-HJM0160",1200,31100,0,20,"R22","220V",15000),
            new UnidadeCondensadoras("Danfoss","OP-HJM0160",1200,36990,5,20,"R22","220V",15000),
            new UnidadeCondensadoras("Danfoss","OP-HJM0160",1200,43370,10,20,"R22","220V",15000),


            new UnidadeCondensadoras("Danfoss","OP-HJZ019",150,586,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ019",150,952,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ019",150,1385,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ019",150,1888,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ019",150,2462,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HJZ022",200,1023,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ022",200,1449,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ022",200,1995,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ022",200,2543,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ022",200,3211,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HJZ028",250,1531,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ028",250,2098,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ028",250,2761,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ028",250,3525,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ028",250,4394,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HJZ032",300,1616,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ032",300,2222,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ032",300,2944,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ032",300,3785,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ032",300,4746,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HJZ036",350,1779,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ036",350,2490,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ036",350,3325,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ036",350,4286,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ036",350,5372,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HJZ040",400,2122,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ040",400,2972,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ040",400,3977,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ040",400,5145,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ040",400,6479,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HJZ044",450,2168,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ044",450,3101,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ044",450,4199,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ044",450,5471,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ044",450,6920,-10,-6,"R404A","220V",1),


            new UnidadeCondensadoras("Danfoss","OP-HJZ050",500,2630,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ050",500,3652,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ050",500,4850,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ050",500,6234,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ050",500,7612,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HJZ056",550,2918,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ056",550,4082,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ056",550,5437,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ056",550,6985,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ056",550,8723,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HJZ064",650,3316,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ064",650,4548,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ064",650,5980,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ064",650,7615,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ064",650,9450,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HJZ072",700,3973,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ072",700,5393,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ072",700,7066,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ072",700,9002,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ072",700,11210,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HJZ080",800,4677,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ080",800,6205,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ080",800,7989,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ080",800,10040,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HJZ080",800,12350,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HGZ100",900,4788,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ100",900,6603,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ100",900,8697,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ100",900,11070,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ100",900,13720,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HGZ125",1000,7162,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ125",1000,9464,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ125",1000,12130,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ125",1000,15180,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ125",1000,18630,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HGZ0144",1100,8503,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ0144",1100,11080,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ0144",1100,14060,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ0144",1100,17460,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ0144",1100,21290,-10,-6,"R404A","220V",1),

            new UnidadeCondensadoras("Danfoss","OP-HGZ0160",1200,9256,-30,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ0160",1200,11980,-25,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ0160",1200,15110,-20,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ0160",1200,18660,-15,-6,"R404A","220V",1),
            new UnidadeCondensadoras("Danfoss","OP-HGZ0160",1200,22640,-10,-6,"R404A","220V",1)
    );

    public static List<UnidadeCondensadoras> getCatalogo() {
        return catalogo;
    }

}
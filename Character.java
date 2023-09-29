import java.util.Random;
import java.util.Scanner;

public class Character {
    private String nome;
    private int pv;
    private int forca;
    private int constituicao;
    private int agilidade;
    private int destreza;
    private Weapon weapon;
    private Armor armor;
    private boolean defesaDobrada;

    public Character(String nome) {
        this.nome = nome;
        this.forca = 0;
        this.constituicao = 0;
        this.agilidade = 0;
        this.destreza = 0;
        this.pv = 0;
    }

    public void distribuirPontos(int pontos) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (pontos > 0) {
                System.out.println("Pontos disponíveis: " + pontos);
                System.out.println("1. Adicionar pontos em Força");
                System.out.println("2. Adicionar pontos em Constituição");
                System.out.println("3. Adicionar pontos em Agilidade");
                System.out.println("4. Adicionar pontos em Destreza");
                System.out.println("5. Finalizar distribuição");

                int escolha = scanner.nextInt();
                int pontosEscolhidos;

                switch (escolha) {
                    case 1:
                        System.out.print("Digite a quantidade de pontos de Força a adicionar: ");
                        pontosEscolhidos = scanner.nextInt();
                        if (pontosEscolhidos > pontos || pontosEscolhidos < 0) {
                            System.out.println("Quantidade de pontos inválida.");
                            continue;
                        }
                        forca += pontosEscolhidos;
                        pontos -= pontosEscolhidos;
                        break;
                    case 2:
                        System.out.print("Digite a quantidade de pontos de Constituição a adicionar: ");
                        pontosEscolhidos = scanner.nextInt();
                        if (pontosEscolhidos > pontos || pontosEscolhidos < 0) {
                            System.out.println("Quantidade de pontos inválida.");
                            continue;
                        }
                        constituicao += pontosEscolhidos;
                        pontos -= pontosEscolhidos;
                        break;
                    case 3:
                        System.out.print("Digite a quantidade de pontos de Agilidade a adicionar: ");
                        pontosEscolhidos = scanner.nextInt();
                        if (pontosEscolhidos > pontos || pontosEscolhidos < 0) {
                            System.out.println("Quantidade de pontos inválida.");
                            continue;
                        }
                        agilidade += pontosEscolhidos;
                        pontos -= pontosEscolhidos;
                        break;
                    case 4:
                        System.out.print("Digite a quantidade de pontos de Destreza a adicionar: ");
                        pontosEscolhidos = scanner.nextInt();
                        if (pontosEscolhidos > pontos || pontosEscolhidos < 0) {
                            System.out.println("Quantidade de pontos inválida.");
                            continue;
                        }
                        destreza += pontosEscolhidos;
                        pontos -= pontosEscolhidos;
                        break;
                    case 5:
                        if (pontos > 0) {
                            System.out.println("Você ainda tem pontos disponíveis para distribuir.");
                            break;
                        }
                        return;
                    default:
                        System.out.println("Escolha inválida.");
                }
            }
        }

        pv = rolarD6() + rolarD6() + rolarD6() + constituicao;
    }

    private int rolarD6() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    public void equiparArma(Weapon weapon) {
        this.weapon = weapon;
    }

    public void equiparArmadura(Armor armor) {
        this.armor = armor;
    }

    public int calcularDano() {
        if (weapon != null) {
            if (weapon.getCategoria().equals("pesada")) {
                int constanteDano = weapon.getConstanteDano();
                return rolarD12() + (int) (1.5 * forca) + constanteDano;
            } else {
                int constanteDano = weapon.getConstanteDano();
                return rolarD6() + rolarD6() + rolarD4() + destreza + constanteDano;
            }
        } else {
            // Lida com o caso em que o personagem não possui arma equipada.
            return 0;
        }
    }

    private int rolarD12() {
        Random random = new Random();
        return random.nextInt(12) + 1;
    }

    private int rolarD4() {
        Random random = new Random();
        return random.nextInt(4) + 1;
    }

    public int calcularDefesa() {
        if (armor != null) {
            int constanteDefesa = armor.getConstanteDefesa();
            return (int) (1.5 * constituicao) + constanteDefesa;
        } else {
            // Lida com o caso em que o personagem não possui armadura equipada.
            return 0;
        }
    }

    public int getPV() {
        return pv;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public String getNome() {
        return null;
    }

    public String getForca() {
        return null;
    }

    public String getConstituicao() {
        return null;
    }

    public String getDestreza() {
        return null;
    }

    public Weapon getWeapon() {
        return null;
    }

    public void setPV(int i) {
    }

    public void setDefesaDobrada(boolean b) {
    }

    public boolean isDefesaDobrada() {
        return false;
    }
}

import java.util.Random;
import java.util.Scanner;

public class JogoRPG {
    public static void main(String[] args, Scanner scanner2) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("SEJA BEM VINDO");
        System.out.println("");

        int opcao;
        Character character = null;
        Potion potion = new Potion(3);
        int nivelJogador = 0;

        do {
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. COMECAR JOGO");
            System.out.println("2. HISTORIA DO JOGO");
            System.out.println("3. FECHAR JOGO");
            System.out.println("");
            System.out.print("OPÇÃO: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println("VAMOS COMEÇAR.");
                        System.out.println("");
                        nivelJogador = 0;
                        character = criarPersonagem(scanner2);
                        while (character.getPV() > 0) {
                            Adversary adversary = gerarAdversarioAleatorio(nivelJogador);
                            combate(character, adversary, potion);
                            if (character.getPV() <= 0) {
                                System.out.println("VOCÊ MORREU.");
                                System.out.println("");
                                break;
                            } else {
                                nivelJogador++;
                            }
                            if (nivelJogador == 1 && character.getPV() > 0) {
                                System.out.println("VOCÊ SUBIU DE NIVEL!");
                                System.out.println("VOCÊ RECEBEU 5 PONTOS DE ATRIBUTOS.");
                                character.distribuirPontos(5);
                                System.out.println("ESCOLHA UMA ARMA NOVA:");
                                Weapon novaArma = escolherArma(nivelJogador, scanner);
                                character.equiparArma(novaArma);
                            } else if (nivelJogador == 2 && character.getPV() > 0) {
                                System.out.println("VOCÊ SUBIU DE NIVEL!");
                                System.out.println("VOCÊ RECEBEU 10 PONTOS DE ATRIBUTOS.");
                                character.distribuirPontos(10);
                                System.out.println("ESCOLHA UMA ARMA:");
                                System.out.println("");
                                Armor novaArmadura = escolherArmadura(nivelJogador, scanner);
                                character.equiparArmadura(novaArmadura);
                            } else if (nivelJogador == 3 && character.getPV() > 0) {
                                System.out.println("VOCÊ VENCEU!\n");
                                break;
                            }
                        }
                        character = null;
                        break;
                    case 2:
                        System.out.println("HISTORIA SELECIONADA.");
                        historiaDoJogo(scanner);
                        break;
                    case 3:
                        System.out.println("FECHANDO O JOGO.");
                        break;
                    default:
                        System.out.println("OPÇÃO INVALIDA.");
                }
            } else {
                System.out.println("OPÇÃO INVALIDA");
                scanner.nextLine(); // Limpa a entrada inválida
                opcao = 0; // Defina uma opção inválida para entrar no loop novamente
            }
        } while (opcao != 3);

        scanner.close(); // Feche o Scanner no final do programa
    }

    private static Character criarPersonagem(Scanner scanner) {
        System.out.print("DIGITE SEU NOME ");
        scanner.nextLine(); // Limpa o buffer
        String nome = scanner.nextLine();

        Character character = new Character(nome);

        System.out.println("DISTRIBUA SEUS 18 PONTOS DE ATRIBUTOS:");
        character.distribuirPontos(18);

        System.out.println("ESCOLHA UMA ARMA LEGAL PARA COMEÇAR:");
        Weapon armaInicial = escolherArma(0, scanner);
        character.equiparArma(armaInicial);

        System.out.println("ESCOLHA UMA ARMADURA LEGAL PARA COMEÇAR:");
        Armor armaduraInicial = escolherArmadura(0, scanner);
        character.equiparArmadura(armaduraInicial);

        System.out.println("PERSONAGEM COMPLETOPersonagem criado com sucesso!");
        System.out.println("NOME: " + character.getNome());
        System.out.println("PONTOS DE VIDA (PV): " + character.getPV());
        System.out.println("FORÇA: " + character.getForca());
        System.out.println("CONSTITUIÇÃO: " + character.getConstituicao());
        System.out.println("AGILIDADE: " + character.getAgilidade());
        System.out.println("DESTREZA: " + character.getDestreza());
        System.out.println("ARMA EQUIPADA: " + character.getWeapon().getCategoria());
        System.out.println("DANO DA ARMA: " + character.calcularDano());
        System.out.println("ARMADURA EQUIPADA: DEFESA " + character.calcularDefesa());

        return character;
    }

    private static Weapon escolherArma(int nivel, Scanner scanner) {
        if (nivel == 0) {
            System.out.println("ESCOLHA A ARMA MAIS LEGAL PARA VOCÊ:");
            System.out.println("1. MACHADO DE GUERRA - FORTE (DANO: 5)");
            System.out.println("2. ESPADA ANTIGA - MÉDIO (DANO: 4)");
            System.out.println("3. FACAS AFIADAS - LEVE (DANO: 3)");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    return new Weapon("FORTE", 5);
                case 2:
                    return new Weapon("MÉDIO", 4);
                case 3:
                    return new Weapon("LEVE", 3);
                default:
                    System.out.println("OPÇÃO INVALIDA, O MACHADO DE GUERRA FOI SELECIONADO.");
                    return new Weapon("FORTE", 5);
            }
        } else if (nivel == 1) {
            System.out.println("ESCOLHA A ARMA MAIS LEGAL PARA VOCÊ:");
            System.out.println("1. W (SIM, ISSO É UMA ARMA) (DANO: 9)");
            System.out.println("2. KATANA (DANO: 7)");
            System.out.println("3. PORRETE DESTRUIDOR (DANO: 5)");

            int escolha2 = scanner.nextInt();

            switch (escolha2) {
                case 1:
                    return new Weapon("FORTE", 9);
                case 2:
                    return new Weapon("MÉDIA", 7);
                case 3:
                    return new Weapon("LEVE", 5);
                default:
                    System.out.println("OPÇÃO INVALIDA, W FOI SELECIONADO.");
                    return new Weapon("FORTE", 9);
            }
        }
        return null;
    }

    private static Armor escolherArmadura(int nivel, Scanner scanner) {
        if (nivel == 0) {
            System.out.println("ESCOLHA A ARMADURA:");
            System.out.println("1. ARMADURA DE COURO - LEVE (DEFESA: 3)");
            System.out.println("2. ARMADURA DE MALHA - MÉDIO (DEFESA: 4)");
            System.out.println("3. ARMADURA DE FERRO - FORTE (DEFESA: 5)");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    return new Armor(3);
                case 2:
                    return new Armor(4);
                case 3:
                    return new Armor(5);
                default:
                    System.out.println("OPÇÃO INVALIDA, ARMADURA DE COURO SELECIONADA");
                    return new Armor(3);
            }
        } else if (nivel == 2) {
            System.out.println("ESCOLHA SUA AARMADURA:");
            System.out.println("1. BLUSA FLORIDA DO GIROTTO (DEFESA: 7)");
            System.out.println("2. ARMADURA SOMBRIA (DEFESA: 8)");
            System.out.println("3. MANTO SAGRADO (DEFESA: 9)");

            int escolha2 = scanner.nextInt();

            switch (escolha2) {
                case 1:
                    return new Armor(7);
                case 2:
                    return new Armor(8);
                case 3:
                    return new Armor(9);
                default:
                    System.out.println("OPÇÃO INVALIDA, A BLUSA FLORIDA DO GIROTTO FOI SELECIONADA.");
                    return new Armor(7);
            }
        }
        return null;
    }

    private static void historiaDoJogo(Scanner scanner) {
        System.out.println("Em um reino mergulhado na escuridão, onde as terras estão infestadas por criaturas malignas e a população ");
        System.out.println("vive amedrontada, você é um destemido caçador de recompensas. Foi convocado pelo líder da cidade");
        System.out.println("fortificada de Faeloria para enfrentar uma ameaça crescente que assola a região. Goblins, orcs e");
        System.out.println("corruptores, liderados por um líder sombrio conhecido como o Senhor das Sombras, têm causado devastação.");
        System.out.println("");
        System.out.println("Inicialmente, você hesitou em aceitar a missão, temendo o poder e a ferocidade dessas criaturas, mas a");
        System.out.println("promessa de uma recompensa generosa e a garantia de que Faeloria pagaria bem pelo serviço convenceram você ");
        System.out.println("a seguir em frente.");
        System.out.println(". . .");
        System.out.println("Pressione Enter para ir até sua aventura...");
        System.out.println("");
        scanner.nextLine(); // Aguarde a entrada do Enter
    }

    private static Adversary gerarAdversarioAleatorio(int nivel) {
        Random random = new Random();
        int escolha = random.nextInt(3);
        int escolha2 = random.nextInt(2);
        int escolha3 = random.nextInt(1);

        if (nivel == 0) {

            switch (escolha) {
                case 0:
                    return new Adversary("GOBLIN", 40, 16, 3, 2);
                case 1:
                    return new Adversary("LADRÃO", 30, 12, 2, 3);
                case 2:
                    return new Adversary("OGRO", 50, 20, 4, 1);
                default:
                    return new Adversary("INIMIGO ALEATORIO", 25, 14, 3, 2);
            }
        } else if (nivel == 1) {

            switch (escolha2) {
                case 0:
                    return new Adversary("OGRO BOMBADO", 60, 16, 5, 4);
                case 1:
                    return new Adversary("CAPETA", 70, 15, 14, 3);
                default:
                    return new Adversary("INIMIGO ALEATORIO", 25, 14, 3, 2);
            }
        } else if (nivel == 2) {

            switch (escolha3) {
                case 0:
                    return new Adversary("DARK W", 90, 40, 12, 10);
                default:
                    return new Adversary("DARK W", 90, 40, 12, 10);
            }
        }
        return null;
    }

    private static void combate(Character character, Adversary adversary, Potion potion) {
        System.out.println("\nCOMBATE INICIADO COM " + adversary.getNome() + "!");
        Scanner scanner = new Scanner(System.in);
        int rodada = 1;

        while (true) {
            System.out.println("ROUND " + rodada + "\n");
            int agilidadeJogador = character.getAgilidade();
            int agilidadeAdversario = adversary.getAgilidade();
            potion.getPocoesDisponiveis();

            if (agilidadeJogador > agilidadeAdversario) {
                turnoJogador(character, adversary, scanner, potion);
                if (adversary.getPV() <= 0) {
                    System.out.println("VOCÊ VENCEU " + adversary.getNome() + "!");
                    System.out.println("");
                    System.out.println("VOCÊ GANHOU UMA POÇÃO!");
                    System.out.println("");
                    System.out.println("PRESSIONE ENTER PARA CONTINUAR...\n");
                    scanner.nextLine();
                    break;
                }

                turnoAdversario(character, adversary, scanner);
                if (character.getPV() <= 0) {
                    System.out.println(adversary.getNome() + " DERROTOU VOCÊ.");
                    break;
                }
            } else {
                turnoAdversario(character, adversary, scanner);
                if (character.getPV() <= 0) {
                    System.out.println(adversary.getNome() + " DERROTOU VOCÊ.");
                    break;
                }

                turnoJogador(character, adversary, scanner, potion);
                if (adversary.getPV() <= 0) {
                    System.out.println("VOCÊ VENCEU DE " + adversary.getNome() + "!");
                    System.out.println("VOCÊ GANHOU UMA POÇÃO!");
                    System.out.println("PRESSIONE ENTER PARA CONTINUAR...\n");
                    scanner.nextLine();
                    break;
                }
            }

            rodada++;
        }
        scanner.close(); // Feche o Scanner no final do combate
    }

    private static void turnoJogador(Character character, Adversary adversary, Scanner scanner, Potion potion) {
        System.out.println("O QUE DESEJA FAZER?");
        System.out.println("1. ATACAR");
        System.out.println("2. DEFENDER");
        System.out.println("3. POÇÃO");

        int escolha = scanner.nextInt();
        int pocoesDisponiveis = potion.getPocoesDisponiveis();

        switch (escolha) {
            case 1:
                int danoCausado = Math.max(0, character.calcularDano() - adversary.getDefesa());
                if (danoCausado > 0) {
                    adversary.setPV(adversary.getPV() - danoCausado);
                    System.out.println("VOCÊ DEU " + danoCausado + " DE DANO NO " + adversary.getNome() + "!");
                } else {
                    System.out.println(adversary.getNome() + " SE DEFENDEU!");
                }
                break;
            case 2:
                character.setDefesaDobrada(true);
                System.out.println("VOCE DEFENDEU, DEFESA DOBRADA NO PROXIMO ROUND.");
                break;
            case 3:
                if (pocoesDisponiveis > 0) {
                    int cura = rolarD6() + rolarD6() + rolarD6();
                    character.setPV(character.getPV() + cura);
                    pocoesDisponiveis--;
                    System.out.println("VOCE RECUPEROU " + cura + " DA SUA VIDA.");
                } else {
                    System.out.println("ACABARAM AS POÇÕES.");
                }
                break;
            default:
                System.out.println("AÇÃO INVALIDA");
        }

        if (character.isDefesaDobrada()) {
            character.setDefesaDobrada(false);
        }
    }

    private static void turnoAdversario(Character character, Adversary adversary, Scanner scanner) {
        Random random = new Random();
        int escolha = random.nextInt(3);

        switch (escolha) {
            case 0:
                int danoCausado = Math.max(0, adversary.getDano() - character.calcularDefesa());
                if (danoCausado > 0) {
                    character.setPV(character.getPV() - danoCausado);
                    System.out.println(adversary.getNome() + " DEU " + danoCausado + " DE DANO!");
                } else {
                    System.out.println("VOCE SE DEFENDEU DO ATAQUE DE " + adversary.getNome() + "!");
                }
                break;
            case 1:
                adversary.setDefesaDobrada(true);
                System.out.println(adversary.getNome() + " ESTA DEFENDENDO, SUA DEFESA ESTA DOBRADA NO PROXIMO ROUND.");
                break;
            case 2:
                int cura = rolarD6() + rolarD6() + rolarD6();
                adversary.setPV(adversary.getPV() + cura);
                System.out.println(adversary.getNome() + " USOU OUÇAO E CUROU" + cura + " DE VIDA.");
                break;
        }

        if (adversary.isDefesaDobrada()) {
            adversary.setDefesaDobrada(false);
        }
    }

    private static int rolarD6() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}

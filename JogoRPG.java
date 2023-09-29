import java.util.Random;
import java.util.Scanner;

public class JogoRPG {
    public static void main(String[] args, Scanner scanner2) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Reino de Faeloria!");
        System.out.println("");

        int opcao;
        Character character = null;
        Potion potion = new Potion(3);
        int nivelJogador = 0;

        do {
            System.out.println("Menu Principal");
            System.out.println("1. Começar Jogo");
            System.out.println("2. História do Jogo");
            System.out.println("3. Fechar Jogo");
            System.out.println("");
            System.out.print("Opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println("Você Selecionou Começar Jogo.");
                        System.out.println("");
                        nivelJogador = 0;
                        character = criarPersonagem(scanner2);
                        while (character.getPV() > 0) {
                            Adversary adversary = gerarAdversarioAleatorio(nivelJogador);
                            combate(character, adversary, potion);
                            if (character.getPV() <= 0) {
                                System.out.println("FALECEU, foi pro inferno.");
                                System.out.println("");
                                break;
                            } else {
                                nivelJogador++;
                            }
                            if (nivelJogador == 1 && character.getPV() > 0) {
                                System.out.println("Você subiu de nível!");
                                System.out.println("Você recebeu 5 pontos de atributo adicionais.");
                                character.distribuirPontos(5);
                                System.out.println("Escolha uma nova arma:");
                                Weapon novaArma = escolherArma(nivelJogador, scanner);
                                character.equiparArma(novaArma);
                            } else if (nivelJogador == 2 && character.getPV() > 0) {
                                System.out.println("Você subiu de nível!");
                                System.out.println("Você recebeu 10 pontos de atributo adicionais.");
                                character.distribuirPontos(10);
                                System.out.println("Escolha uma nova armadura:");
                                System.out.println("");
                                Armor novaArmadura = escolherArmadura(nivelJogador, scanner);
                                character.equiparArmadura(novaArmadura);
                            } else if (nivelJogador == 3 && character.getPV() > 0) {
                                System.out.println("Você venceu o jogo! Parabéns!\n");
                                break;
                            }
                        }
                        character = null;
                        break;
                    case 2:
                        System.out.println("Você Selecionou História do Jogo.");
                        historiaDoJogo(scanner);
                        break;
                    case 3:
                        System.out.println("Você Selecionou para Fechar o Jogo.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Opção inválida. Digite um número.");
                scanner.nextLine(); // Limpa a entrada inválida
                opcao = 0; // Defina uma opção inválida para entrar no loop novamente
            }
        } while (opcao != 3);

        scanner.close(); // Feche o Scanner no final do programa
    }

    private static Character criarPersonagem(Scanner scanner) {
        System.out.print("Digite o nome do seu personagem: ");
        scanner.nextLine(); // Limpa o buffer
        String nome = scanner.nextLine();

        Character character = new Character(nome);

        System.out.println("Distribua 18 pontos nos atributos:");
        character.distribuirPontos(18);

        System.out.println("Escolha uma arma inicial:");
        Weapon armaInicial = escolherArma(0, scanner);
        character.equiparArma(armaInicial);

        System.out.println("Escolha uma armadura inicial:");
        Armor armaduraInicial = escolherArmadura(0, scanner);
        character.equiparArmadura(armaduraInicial);

        System.out.println("Personagem criado com sucesso!");
        System.out.println("Nome: " + character.getNome());
        System.out.println("Pontos de Vida (PV): " + character.getPV());
        System.out.println("Força: " + character.getForca());
        System.out.println("Constituição: " + character.getConstituicao());
        System.out.println("Agilidade: " + character.getAgilidade());
        System.out.println("Destreza: " + character.getDestreza());
        System.out.println("Arma Equipada: " + character.getWeapon().getCategoria());
        System.out.println("Dano da Arma Equipada: " + character.calcularDano());
        System.out.println("Armadura Equipada: Defesa " + character.calcularDefesa());

        return character;
    }

    private static Weapon escolherArma(int nivel, Scanner scanner) {
        if (nivel == 0) {
            System.out.println("Escolha uma arma:");
            System.out.println("1. Machado Grande - Pesada (Dano: 5)");
            System.out.println("2. Espada Longa - Média (Dano: 4)");
            System.out.println("3. Espada Curta - Leve (Dano: 3)");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    return new Weapon("pesada", 5);
                case 2:
                    return new Weapon("media", 4);
                case 3:
                    return new Weapon("leve", 3);
                default:
                    System.out.println("Opção inválida, a arma pesada foi escolhida por padrão.");
                    return new Weapon("pesada", 5);
            }
        } else if (nivel == 1) {
            System.out.println("Escolha uma arma:");
            System.out.println("1. Espada Vorpal (Dano: 9)");
            System.out.println("2. Martelo Mjolnir (Dano: 7)");
            System.out.println("3. Espada Flamejante (Dano: 5)");

            int escolha2 = scanner.nextInt();

            switch (escolha2) {
                case 1:
                    return new Weapon("pesada", 9);
                case 2:
                    return new Weapon("media", 7);
                case 3:
                    return new Weapon("leve", 5);
                default:
                    System.out.println("Opção inválida, a arma pesada foi escolhida por padrão.");
                    return new Weapon("pesada", 9);
            }
        }
        return null;
    }

    private static Armor escolherArmadura(int nivel, Scanner scanner) {
        if (nivel == 0) {
            System.out.println("Escolha uma armadura:");
            System.out.println("1. Couraça de Couro - Leve (Defesa: 3)");
            System.out.println("2. Couraça de Peles - Média (Defesa: 4)");
            System.out.println("3. Couraça de Escamas - Pesada (Defesa: 5)");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    return new Armor(3);
                case 2:
                    return new Armor(4);
                case 3:
                    return new Armor(5);
                default:
                    System.out.println("Opção inválida, a armadura leve foi escolhida por padrão.");
                    return new Armor(3);
            }
        } else if (nivel == 2) {
            System.out.println("Escolha uma armadura:");
            System.out.println("1. Cota de Malha (Defesa: 7)");
            System.out.println("2. Armadura de Couro Elfo (Defesa: 8)");
            System.out.println("3. Armadura de Malha do Dragão (Defesa: 9)");

            int escolha2 = scanner.nextInt();

            switch (escolha2) {
                case 1:
                    return new Armor(7);
                case 2:
                    return new Armor(8);
                case 3:
                    return new Armor(9);
                default:
                    System.out.println("Opção inválida, a armadura leve foi escolhida por padrão.");
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
                    return new Adversary("Goblin Ladrão", 40, 16, 3, 2);
                case 1:
                    return new Adversary("Diabrete", 30, 12, 2, 3);
                case 2:
                    return new Adversary("Orc", 50, 20, 4, 1);
                default:
                    return new Adversary("Inimigo Aleatório", 25, 14, 3, 2);
            }
        } else if (nivel == 1) {

            switch (escolha2) {
                case 0:
                    return new Adversary("Orc Guerreiro", 60, 16, 5, 4);
                case 1:
                    return new Adversary("Demonio Corruptor", 70, 15, 14, 3);
                default:
                    return new Adversary("Inimigo Aleatório", 25, 14, 3, 2);
            }
        } else if (nivel == 2) {

            switch (escolha3) {
                case 0:
                    return new Adversary("Cavaleiro Infernal", 90, 40, 12, 10);
                default:
                    return new Adversary("Cavaleiro Infernal", 90, 40, 12, 10);
            }
        }
        return null;
    }

    private static void combate(Character character, Adversary adversary, Potion potion) {
        System.out.println("\nIniciando combate contra " + adversary.getNome() + "!");
        Scanner scanner = new Scanner(System.in);
        int rodada = 1;

        while (true) {
            System.out.println("Rodada " + rodada + "\n");
            int agilidadeJogador = character.getAgilidade();
            int agilidadeAdversario = adversary.getAgilidade();
            potion.getPocoesDisponiveis();

            if (agilidadeJogador > agilidadeAdversario) {
                turnoJogador(character, adversary, scanner, potion);
                if (adversary.getPV() <= 0) {
                    System.out.println("Você venceu o combate contra " + adversary.getNome() + "!");
                    System.out.println("");
                    System.out.println("Você recebeu uma poção!");
                    System.out.println("");
                    System.out.println("Pressione Enter para continuar...\n");
                    scanner.nextLine();
                    break;
                }

                turnoAdversario(character, adversary, scanner);
                if (character.getPV() <= 0) {
                    System.out.println(adversary.getNome() + " derrotou você! O jogo acabou.");
                    break;
                }
            } else {
                turnoAdversario(character, adversary, scanner);
                if (character.getPV() <= 0) {
                    System.out.println(adversary.getNome() + " derrotou você! O jogo acabou.");
                    break;
                }

                turnoJogador(character, adversary, scanner, potion);
                if (adversary.getPV() <= 0) {
                    System.out.println("Você venceu o combate contra " + adversary.getNome() + "!");
                    System.out.println("Você recebeu uma poção!");
                    System.out.println("Pressione Enter para continuar...\n");
                    scanner.nextLine();
                    break;
                }
            }

            rodada++;
        }
        scanner.close(); // Feche o Scanner no final do combate
    }

    private static void turnoJogador(Character character, Adversary adversary, Scanner scanner, Potion potion) {
        System.out.println("Agora é sua vez, o que deseja fazer?");
        System.out.println("1. Atacar");
        System.out.println("2. Defender");
        System.out.println("3. Usar Poção");

        int escolha = scanner.nextInt();
        int pocoesDisponiveis = potion.getPocoesDisponiveis();

        switch (escolha) {
            case 1:
                int danoCausado = Math.max(0, character.calcularDano() - adversary.getDefesa());
                if (danoCausado > 0) {
                    adversary.setPV(adversary.getPV() - danoCausado);
                    System.out.println("Você causou " + danoCausado + " de dano em " + adversary.getNome() + "!");
                } else {
                    System.out.println(adversary.getNome() + " se defendeu completamente!");
                }
                break;
            case 2:
                character.setDefesaDobrada(true);
                System.out.println("Você está defendendo. Sua defesa dobrou por uma rodada.");
                break;
            case 3:
                if (pocoesDisponiveis > 0) {
                    int cura = rolarD6() + rolarD6() + rolarD6();
                    character.setPV(character.getPV() + cura);
                    pocoesDisponiveis--;
                    System.out.println("Você usou uma poção e recuperou " + cura + " de vida.");
                } else {
                    System.out.println("Você não tem mais poções disponíveis.");
                }
                break;
            default:
                System.out.println("Ação inválida. Tente novamente.");
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
                    System.out.println(adversary.getNome() + " causou " + danoCausado + " de dano em você!");
                } else {
                    System.out.println("Você se defendeu completamente contra o ataque de " + adversary.getNome() + "!");
                }
                break;
            case 1:
                adversary.setDefesaDobrada(true);
                System.out.println(adversary.getNome() + " está defendendo. Sua defesa dobrou por uma rodada.");
                break;
            case 2:
                int cura = rolarD6() + rolarD6() + rolarD6();
                adversary.setPV(adversary.getPV() + cura);
                System.out.println(adversary.getNome() + " usou uma poção e recuperou " + cura + " de vida.");
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


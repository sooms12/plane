package PLANE;

public class util {
    public static final String black = "\u001B[30m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36m";
    public static final String white = "\u001B[37m";

    public static final String exit = "\u001B[0m";

    public static void calender(){
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│               2023 - 05                 │");
        System.out.println("├─────┬─────┬─────┬─────┬─────┬─────┬─────┤");
        System.out.println("│ "+red+"SUN"+exit+" │ MON │ TUE │ WED │ THU │ FRI │ "+blue+"SAT"+exit+" │");
        System.out.println("├─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
        System.out.println("│     │  01 │  02 │  03 │  04 │  "+red+"05"+exit+" │  "+blue+"06"+exit+" │");
        System.out.println("│  "+red+"07"+exit+" │  08 │  09 │  10 │  11 │  12 │  "+blue+"13"+exit+" │");
        System.out.println("│  "+red+"14"+exit+" │  15 │  16 │  17 │  18 │  19 │  "+blue+"20"+exit+" │");
        System.out.println("│  "+red+"21"+exit+" │  22 │  23 │  24 │  25 │  26 │  "+red+"27"+exit+" │");
        System.out.println("│  "+red+"28"+exit+" │  29 │  30 │  31 │     │     │     │");
        System.out.println("└─────┴─────┴─────┴─────┴─────┴─────┴─────┘");
    }

    public static void plane() {

        System.out.println(red+"┌────────────────────────────────────────────────────────────────────────────────┐"+exit);
        System.out.println(yellow+"│"+exit+"                               "+blue+"___"+exit+"                                              "+yellow+"│"+exit);
        System.out.println(green+"│"+exit+"                               "+blue+"\\\\ \\"+exit+"                                             "+green+"│"+exit);
        System.out.println(cyan+"│"+exit+"                                "+blue+"\\\\ `\\"+exit+"                                           "+cyan+"│"+exit);
        System.out.println(blue+"│"+exit+"             "+blue+"___                 \\\\  \\"+exit+"                                          "+blue+"│"+exit);
        System.out.println(purple+"│"+exit+"            "+blue+"|    \\                \\\\  `\\"+exit+"                                        "+purple+"│"+exit);
        System.out.println(red+"│"+exit+"            "+blue+"|_____\\                \\    \\"+exit+"                                       "+red+"│"+exit);
        System.out.println(yellow+"│"+exit+"            "+blue+"|______\\                \\    `\\"+exit+"                                     "+yellow+"│"+exit);
        System.out.println(green+"│"+exit+"            "+blue+"|       \\                \\     \\"+exit+"                                    "+green+"│"+exit);
        System.out.println(cyan+"│"+exit+"            "+blue+"|      __\\__---------------------------------._."+exit+"                    "+cyan+"│"+exit);
        System.out.println(blue+"│"+exit+"          "+blue+"__|---~~~__o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_[][\\__"+exit+"                 "+blue+"│"+exit);
        System.out.println(purple+"│"+exit+"            "+blue+"|___                         /~      )                \\__"+exit+"           "+purple+"│"+exit);
        System.out.println(red+"│"+exit+"                 "+blue+"~~~---..._______________/      ,/_________________/"+exit+"            "+red+"│"+exit);
        System.out.println(yellow+"│"+exit+"                                        "+blue+"/      /"+exit+"                                "+yellow+"│"+exit);
        System.out.println(green+"│"+exit+"                                      "+blue+"/     ,/"+exit+"                                  "+green+"│"+exit);
        System.out.println(cyan+"│"+exit+"                                     "+blue+"/     /"+exit+"                                    "+cyan+"│"+exit);
        System.out.println(blue+"│"+exit+"                                    "+blue+"/    ,/"+exit+"                                     "+blue+"│"+exit);
        System.out.println(purple+"│"+exit+"                                   "+blue+"/    /"+exit+"                                       "+purple+"│"+exit);
        System.out.println(red+"│"+exit+"                                  "+blue+"//  ,/"+exit+"                                        "+red+"│"+exit);
        System.out.println(yellow+"│"+exit+"                                 "+blue+"//  /"+exit+"                                          "+yellow+"│"+exit);
        System.out.println(green+"│"+exit+"                                "+blue+"// ,/"+exit+"                                           "+green+"│"+exit);
        System.out.println(cyan+"│"+exit+"                               "+blue+"//_/"+exit+"                                             "+cyan+"│"+exit);
        System.out.println(blue+"│"+exit+"                                                                                "+blue+"│"+exit);
        System.out.println(purple+"└────────────────────────────────────────────────────────────────────────────────┘"+exit);
    }
}

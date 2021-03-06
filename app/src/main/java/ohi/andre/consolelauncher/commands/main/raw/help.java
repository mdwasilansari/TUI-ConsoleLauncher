package ohi.andre.consolelauncher.commands.main.raw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ohi.andre.comparestring.Compare;
import ohi.andre.consolelauncher.R;
import ohi.andre.consolelauncher.commands.CommandAbstraction;
import ohi.andre.consolelauncher.commands.ExecutePack;
import ohi.andre.consolelauncher.commands.main.MainPack;
import ohi.andre.consolelauncher.tuils.Tuils;

public class help implements CommandAbstraction {

    @Override
    public String exec(ExecutePack pack) throws Exception {
        MainPack info = (MainPack) pack;
        CommandAbstraction cmd = info.get(CommandAbstraction.class, 0);
        int res = cmd == null ? R.string.output_commandnotfound : cmd.helpRes();
        return info.res.getString(res);
    }

    @Override
    public int helpRes() {
        return R.string.help_help;
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public int[] argType() {
        return new int[]{CommandAbstraction.COMMAND};
    }

    @Override
    public int priority() {
        return 5;
    }

    @Override
    public String[] parameters() {
        return null;
    }

    @Override
    public String onNotArgEnough(ExecutePack pack, int nArgs) {
        MainPack info = (MainPack) pack;
        List<String> toPrint = new ArrayList<>(Arrays.asList(info.commandGroup.getCommandNames()));

        Collections.sort(toPrint, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return Compare.alphabeticCompare(lhs, rhs);
            }
        });

        Tuils.addPrefix(toPrint, Tuils.DOUBLE_SPACE);
        Tuils.addSeparator(toPrint, Tuils.TRIBLE_SPACE);
        Tuils.insertHeaders(toPrint, true);

        return Tuils.toPlanString(toPrint, "");
    }

    @Override
    public String onArgNotFound(ExecutePack pack) {
        MainPack info = (MainPack) pack;
        return info.res.getString(R.string.output_commandnotfound);
    }

}

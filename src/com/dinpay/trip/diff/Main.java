package com.dinpay.trip.diff;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length < 4 || args[0].equals("help")){
            System.out.println("使用: java -jar KudouDiffTool.jar <method> <oldFile> <newFile> <patchFile>");
            System.out.println("参数说明：");
            System.out.println("    method: ");
            System.out.println("        diff: 生成差分包");
            System.out.println("        patch: 合成差分包");
            System.out.print("\n");
            System.out.println("    oldFile: 原始文件");
            System.out.println("    newFile: 新文件");
            System.out.println("    patchFile: 差分包");
            System.exit(-1);
        }
        File oldFile = new File(args[1]);
        if (!oldFile.exists()) {
            System.out.println("oldFile不存在");
            System.exit(-1);
        }
        File newFile = new File(args[2]);
        if (!oldFile.exists()) {
            System.out.println("newFile不存在");
            System.exit(-1);
        }
        File patchFile = new File(args[3]);
        try {
            if (patchFile.exists())
                patchFile.delete();
            else
                patchFile.createNewFile();
            if (args[0].equals("diff"))
                BSDiff.bsdiff(oldFile, newFile, patchFile);
            else if (args[0].equals("patch"))
                BSPatch.patchFast(oldFile, newFile, patchFile, 0);
            System.exit(0);
        } catch (Exception e) {
            System.out.println("差分失败: " + e);
            System.exit(-1);
        }
    }
}

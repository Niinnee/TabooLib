package me.skymc.taboolib.itemnbtapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class NBTFile extends NBTCompound {

    private final File file;
    private Object nbt;

    public NBTFile(File file) throws IOException {
        super(null, null);
        this.file = file;
        if (file.exists()) {
            FileInputStream inputsteam = new FileInputStream(file);
            nbt = NBTReflectionUtil.readNBTFile(inputsteam);
        } else {
            nbt = NBTReflectionUtil.getNewNBTTag();
            save();
        }
    }

    public void save() throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileOutputStream outStream = new FileOutputStream(file);
        NBTReflectionUtil.saveNBTFile(nbt, outStream);
    }

    public File getFile() {
        return file;
    }

    @Override
    protected Object getCompound() {
        return nbt;
    }

    @Override
    protected void setCompound(Object compound) {
        nbt = compound;
    }

}

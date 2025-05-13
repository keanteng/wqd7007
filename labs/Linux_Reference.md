# General Command Reference

## General

```bash
cd ..
cd <folder>
mkdir <folder>
ls
ls -l
uptime
clear
exit
cat <file>
echo <text>
nano <file>
vim <file>
rm <file>
rm -rf <folder>
mv <file> <new_file>
cp <file> <new_file>
```

> We can also create script file `.sh` and run it with `bash <file>.sh`. It is a good way to automate tasks.

## Debugging:

- Not enough disk space on VM:

```bash
sudo apt-get clean
sudo apt-get autoremove
```
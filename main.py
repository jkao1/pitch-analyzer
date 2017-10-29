import os

os.chdir('f0/')

def main():
    files = os.listdir(os.getcwd())
    for f in files:
        read = open(f, 'rU')
        s = read.read()
        read.close()

        # filename structure: userNN-toneN.txt
        filenameComponents = f.split('-')
        try:
            userID = str(filenameComponents[0])
            toneNum = str(filenameComponents[1][0])
            identifier = userID + " " + toneNum
            newFilename = userID + "-" + toneNum + ".txt"
            if s.find( identifier ) == -1:
                write = open(newFilename, 'w')
                write.write(identifier + "\n" + s)
                write.close()
        except IndexError:
            print(filenameComponents)

main()

def fix():
    files = os.listdir(os.getcwd())
    for f in files:
        read = open(f, 'rU')
        s = read.read()
        read.close()
        s = s[s.find('Time_s'):]
        write = open(f, 'w')
        write.write(s)
        write.close()

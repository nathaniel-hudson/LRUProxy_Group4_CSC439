# LRUProxy_Group4_CSC439 #
LRU Proxy implementation for CSC 439 (Software Testing &amp; Maintenance) group project assignment.

## Setup ##

### Clone the Repository ###
1. Open your terminal
2. Clone the repository
    * "git clone git@github.com:Nchudson95/LRUProxy_Group4_CSC439.git"

### Running Ant (Linux/MacOS Instructions) ###
1. Download and extract Ant from the [Apache website.](http://ant.apache.org/)
2. Move the Ant files to your desired directory.
    * "mv apache-ant-1.9.7 /usr/local/bin"
3. Edit your .bash_profile with your terminal editor of choice and add the Ant binary to your PATH variable.
    * "vim ~/.bash_profile"
    * "export ANT_HOME=/usr/local/bin/apache-ant-1.9.7"
    * "export PATH=$PATH:$ANT_HOME/bin"
4. Restart your terminal.
5. Change to the root directory of the project.
    * "cd ~/Documents/MyProjects/Csc439Project"
6. Run the Ant build file.
    * "ant"

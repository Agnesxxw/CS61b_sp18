# 1. Version Control System
>* Version control systems are tools to keep track of changes to files over time
>* Git it a distributed version control system as opposed to a centralized version control system. 
>* We call the entire history of an entire project a “repository”. 
# 2. Git Command
1. What git init does is tells the git version control system that we want to track the history of the current directory.**Currently no file inside**
```java
$ git init
```
2. To store everything in the repository, we need to first add files. **Still haven’t stored our recipe in the repository**(just added the file to the list of files to track)
```python
$ git add /users/xiweixu/……/
```
3. Check Status. The “changes to be committed” portion of the output lists all files that are currently being tracked and whose changes are ready be committed (i.e. that are ready to be put in the safe)
```java
$ git status
```
4. Stick copies of file into the repository (i.e. into the safe). When executed, the commit command stores a snapshot（commit is a snapshot of both the names and contents of the files） of all added files into the repository.This snapshot of our work is now safe forever and ever.-m command lets us add a message to the commit(hint about the change)
```java
$ git commit -m " "
```
5. See evidence of snapshot
```java
$ git log

commit 9f955d85359fc8e4504d7220f13fad34f8f2c62b//id of the commit
Author: Sandra Upson <sandra@Sandras-MacBook-Air.local>
Date:   Sun Jan 17 19:06:48 2016 -0800

    added tofu recipes

```
6. peek inside of the commit. In order to see previous commits, you can use the log command
```java 
$ git show 9f955d85359fc8e4504d7220f13fad34f8f2c62b
```
7. If change the content, we need to take a picture of our new file(add) before we can create the new panorama(change) that we want to put in the safe(commit)
```java
$ subl ./tofu/kung_pao_tofu.txt
$ git add ./tofu/kung_pao_tofu.txt
$ git commit -m "added bok choy"
$ git log

commit cfcc8cbd88a763712dec2d6bd541b2783fa1f23b
Author: Sandra Upson <sandra@Sandras-MacBook-Air.local>
Date:   Sun Jan 17 19:24:45 2016 -0800

    added bok choy

commit 9f955d85359fc8e4504d7220f13fad34f8f2c62b
Author: Sandra Upson <sandra@Sandras-MacBook-Air.local>
Date:   Sun Jan 17 19:06:48 2016 -0800

    added tofu recipes
```
8. Roll back to previous version.
>* **The checkout command does not change the commit history. The entire point of git is to create a log of everything that has EVER happened to our files.**
>* **checkout actually also does an automatic git add on any files that change as a result of the rollback**
>* If want tou take a new snapshot of this version, use commit command again
```java
$ git checkout 9f955d85359fc8e4504d7220f13fad34f8f2c62b ./recipes/tofu // the id of that version
````

9. **Make sure to specify a file (or directory) when you use checkout.**
## 2.1 Summary
>* git init: Creates a box in which to permanently store panoramic pictures.
>* git add: Takes a temporary photo of one thing that can be assembled into a panoramic photo later.
>* git commit: Assembles all available temporary photos into a panoramic photo. Also destroys all temporary photos.
>* git log: Lists all the panoramic photos we’ve ever taken.
>* git show: Looks at what is in a particular panoramic photo.
>* git checkout: Rearranges files back to how they looked in a given panoramic photo. Does not affect the panormiac photos in your box in any way.

# 3. Local Repositories
## 3.1 Track & Untracked
1. **untracked files**: These files have either never been tracked or were removed from tracking. Git is not maintaining history for these files.

2. **tracked files**: These files have been added to the Git repository and can be in various stages of modification: unmodified, modified, or staged.
>* An unmodified file is one that has had no new changes since the last version of the files was added to the Git repo.
>* A modified file is one that is different from the last one Git has saved.
>* A staged file is one that a user has designated as part of a future commit (usually through use of the git add command). We can think of these as files which have lights shining upon them.

## 3.2 Staging & Committing
A commit is a specific snapshot of your working directory at a particular time. Users must specify what exactly composes the snapshot by staging files.
```java
$ git add FILE
$ git commit -m MESSAGE//Your message should be descriptive and explain what changes your commit makes to your code.
```
**it is a good idea to commit your code as often as possible. Whenever you make significant (or even minor) changes to your code, make a commit. If you are trying something out that you might not stick with, commit it**
## 3.3 Undoing Changes
**Please note that while Git revolves around the concept of history, it is possible to lose your work if you revert with some of these undo commands.**
1. This will take the file’s status back to modified, leaving changes intact. Unstage a file that you **haven’t yet committed:**
```java
$ git reset HEAD [file]
```
2. Amend latest commit (changing commit message or add forgotten files):
```java
$ git add [forgotten-file]
$ git commit --amend//This new amended commit will replace the previous commit
```
3. Revert a file to its state at the time of the most recent commit:(Change after commit, revert this change to the nearest commit version or add version)
```java
$ git checkout -- [file]
```
# 4. Remote Repos
- **git clone [remote-repo-URL]**: Makes a copy of the specified repository, but on your local computer. Also creates a working directory that has files arranged exactly like the most recent snapshot in the download repository. Also records the URL of the remote repository for subsequent network data transfers, and gives it the special remote-repo-name “origin”.
- **git remote add [remote-repo-name] [remote-repo-URL]**: Records a new location for network data transfers.
- **git remote -v**: Lists all locations for network data transfers.
- **git pull [remote-repo-name] master**: Get the most recent copy of the files as seen in remote-repo-name
- **git push [remote-repo-name] master**: Pushes the most recent copy of your files to the remote-repo-name.





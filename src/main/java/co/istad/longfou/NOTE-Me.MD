Windows PowerShell 5.1
Copyright (C) Microsoft Corporation. All rights reserved.

PS D:\ISTAD\ITE3rd\spring\ite-3rd-ecommerce> git add .
warning: in the working copy of '.gitignore', LF will be replaced by CRLF the next time Git touches it
PS D:\ISTAD\ITE3rd\spring\ite-3rd-ecommerce> git commit -m "complete task"
[fou 0217898] complete task
6 files changed, 76 insertions(+), 9 deletions(-)
rename src/main/java/co/istad/longfou/ecommerce/features/order/{Orders.java => Order.java} (84%)
create mode 100644 src/main/java/co/istad/longfou/ecommerce/features/order/OrderMapper.java
PS D:\ISTAD\ITE3rd\spring\ite-3rd-ecommerce> git push origin fou
Enumerating objects: 32, done.
Counting objects: 100% (32/32), done.
Delta compression using up to 16 threads
Compressing objects: 100% (15/15), done.
Writing objects: 100% (18/18), 3.56 KiB | 3.56 MiB/s, done.
Total 18 (delta 6), reused 0 (delta 0), pack-reused 0 (from 0)
remote: Resolving deltas: 100% (6/6), completed with 6 local objects.
To https://github.com/Longfou1900/SprinBoot-JpaSpring04-07-2026.git
71df5fd..0217898  fou -> fou

Windows PowerShell 5.1
Copyright (C) Microsoft Corporation. All rights reserved.

PS D:\ISTAD\ITE3rd\spring\ite-3rd-ecommerce> git add .
PS D:\ISTAD\ITE3rd\spring\ite-3rd-ecommerce> git commit -m "completed task"
[fou 6325e95] completed task
2 files changed, 8 insertions(+), 6 deletions(-)
PS D:\ISTAD\ITE3rd\spring\ite-3rd-ecommerce> git push
fatal: The current branch fou has no upstream branch.
To push the current branch and set the remote as upstream, use

    git push --set-upstream origin fou

To have this happen automatically for branches without a tracking
upstream, see 'push.autoSetupRemote' in 'git help config'.

PS D:\ISTAD\ITE3rd\spring\ite-3rd-ecommerce> git push origin fou
Enumerating objects: 25, done.
Counting objects: 100% (25/25), done.
Delta compression using up to 16 threads
Compressing objects: 100% (10/10), done.
Writing objects: 100% (13/13), 1.06 KiB | 1.06 MiB/s, done.
Total 13 (delta 5), reused 0 (delta 0), pack-reused 0 (from 0)
remote: Resolving deltas: 100% (5/5), completed with 5 local objects.
To https://github.com/Longfou1900/SprinBoot-JpaSpring04-07-2026.git
0217898..6325e95  fou -> fou

Windows PowerShell 5.1
Copyright (C) Microsoft Corporation. All rights reserved.

PS D:\ISTAD\ITE3rd\spring\ite-3rd-ecommerce> git add .
PS D:\ISTAD\ITE3rd\spring\ite-3rd-ecommerce> git commit -m "completed 6 World tasks"
[fou ced5949] completed 6 World tasks                                                            
9 files changed, 220 insertions(+), 34 deletions(-)                                             
create mode 100644 src/main/java/co/istad/longfou/ecommerce/features/order/OrderServiceImpl_OLD.java                                                                                             
PS D:\ISTAD\ITE3rd\spring\ite-3rd-ecommerce> git push origin fou
Enumerating objects: 40, done.
Counting objects: 100% (40/40), done.
Delta compression using up to 16 threads
Compressing objects: 100% (18/18), done.
Writing objects: 100% (21/21), 3.80 KiB | 1.90 MiB/s, done.
Total 21 (delta 9), reused 0 (delta 0), pack-reused 0 (from 0)
remote: Resolving deltas: 100% (9/9), completed with 7 local objects.
To https://github.com/Longfou1900/SprinBoot-JpaSpring04-07-2026.git
6325e95..ced5949  fou -> fou
PS D:\ISTAD\ITE3rd\spring\ite-3rd-ecommerce> 
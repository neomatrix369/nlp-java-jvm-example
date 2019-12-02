# Exploring NLP concepts from inside a Java-based Jupyter notebook

## Getting started

You will need the following from this point forward:

- Git client 2.x or higher (an account on GitHub to fork the repo)
- Java 11 or higher (suggest also install [GraalVM CE](https://www.graalvm.org/) 19.x or higher)
- Docker CE 19.x or higher and check it is running before going further
- Ability to run [shell scripts](../../../README.md#scripts-provided) (also see scripts in this folder) from the CLI
- Understand reading/writing shell scripts (optional)
- Open an account on [Valohai, its FREE](https://app.valohai.com/accounts/signup/)! 
- And thereafter create a Token from the Dashboard in order to be able to communicate with the cloud service from the command-line (using the [Valohai CLI](https://docs.valohai.com/valohai-cli/index.html) client)
    - Once your account is created and you are logged in
    - Go to the [Authentication tab](https://app.valohai.com/auth/) via your [Profile page](https://app.valohai.com/profile/)
    - Click on [Manage Tokens](https://app.valohai.com/auth/tokens/) under API Tokens
    - Click on the [Generate a new token] button
    - The token appears near the address bar of the browser (copy it quickly)
    - Save this token in an environment variable in `.bash_profile` or `.bashrc` or `.zshrc`
    - source the above startup script for the setting to reflect in your environment

Note: At the time of the writing version 1.9.1 of [Apache OpenNLP](https://opennlp.apache.org/) was available.

We have put [together scripts](https://github.com/valohai/nlp-java-jvm-example#scripts-provided) (also see scripts in this folder) to make these steps easy for everyone.

Once inside the `nlp-java-jvm-example` folder we will see the following files:

    LICENSE.txt      
    README.md        
    docker-runner.sh     <=== only this one concerns us at startup
    images
    shared               <=== created just when you run the container

Once done with the above steps, please return to the previous page/section.

## Running the Jupyter notebook container

At your local machine command prompt while at the root of the project, do this:

    $ export VALOHAI_TOKEN=<your generated token from one of the above steps>
    ### The above is optional is you have already saved it in your startup scripts and applied it to the environment
    
    $ ./docker-runner.sh --notebookMode --runContainer
    
    ### the above script will automatically detect the token and proceed

You will see this on startup if you have set the `VALOHAI_TOKEN`:

    ...
    The expected environment variable VALOHAI_TOKEN has been set.

Or there is a chance you get this, with a prompt to enter your token (or paste it) if it is not set:

    Enter (or paste) the Valohai token you generated from your account on valohai.com: _
    
    Running container neomatrix369/nlp-java:0.2
    
    a6918a2bb5c8886cfaa086afec37cefec4a0a560dbbc17deaab368d81fcc967d
            1.08 real         0.06 user         0.03 sys
    **********************************
    Running container in detached mode
    **********************************
    ...(snipped)

Once the container kicks in, you will see something like this, in the Docker container console:

    **********************************
    Running container in detached mode
    **********************************
    
    Displaying the missed log messages for container 61c9b25cb529
    
    --- VALOHAI NOTEBOOK SERVER --- http://127.0.0.1:8888
    
    Set username to: jovyan
    usermod: no changes
    Executing the command: jupyter notebook --notebook-dir=/home/jovyan/work --NotebookApp.token=
    <--snipped-->
    [I 20:39:56.056 NotebookApp] The Jupyter Notebook is running at:
    [I 20:39:56.056 NotebookApp] http://61c9b25cb529:8888/
    [I 20:39:56.056 NotebookApp] Use Control-C to stop this server and shut down all kernels (twice to skip confirmation).
    <--snipped-->
    JDK_TO_USE=GRAALVM
    openjdk version "11.0.5" 2019-10-15
    OpenJDK Runtime Environment (build 11.0.5+10-jvmci-19.3-b05-LTS)
    OpenJDK 64-Bit GraalVM CE 19.3.0 (build 11.0.5+10-jvmci-19.3-b05-LTS, mixed mode, sharing)
    
    ****************************************************
    Attaching back to container, with ID 61c9b25cb529
    ****************************************************
    
    You can terminate your Jupyter session with a Ctrl-C
    
    [W 17:48:44.278 NotebookApp] 404 GET /static/components/react/react-dom.production.min.js (172.17.0.1) 2.29ms referer=http://localhost:8888/tree?
    [<--snipped-->]

Once done with the above steps, please return to the previous page/section.

## Installing Apache OpenNLP in the container

### From the command-line interface

Firstly note the container id mentioned in the running Docker container console (see above), in the case of my console window:

    ...
    Opening Jupyter Notebook in a browser:
     http://localhost:8888
    
    ****************************************************
    Attaching back to container, with ID 1bc4ba4e01f8   <== container id embedded
    ****************************************************     
    ...

Open a new console window, and then run this command to start a new session into the same container (you know this by checking with the `docker ps` command, there is only one container with the Jupyter notebook running):


    $ docker exec -it 1bc4ba4e01f8 /bin/bash
    
    ### the container id in your case will differ in the above command

and it will give you this prompt, where you can proceed with the commands:

    root@1bc4ba4e01f8:~# 
    root@1bc4ba4e01f8:~$ cd opennlp
    root@1bc4ba4e01f8:~$ ./opennlp.sh

You will see the `apache-opennlp-1.9.1-bin.tar.gz` artifact being downloaded and expanded into the `shared` folder (in case it has not been run previously):


    % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                     Dload  Upload   Total   Spent    Left  Speed
    100 10.6M  100 10.6M    0     0  4225k      0  0:00:02  0:00:02 --:--:-- 4225k
    apache-opennlp-1.9.1/
    apache-opennlp-1.9.1/NOTICE
    apache-opennlp-1.9.1/LICENSE
    apache-opennlp-1.9.1/README.html
    .
    .
    .
    apache-opennlp-1.9.1/lib/jackson-jaxrs-json-provider-2.8.4.jar
    apache-opennlp-1.9.1/lib/jackson-module-jaxb-annotations-2.8.4.jar

Once done with the above steps, please return to the previous page/section.

### From inside the Jupyter notebook

Once you have the Java notebook opened (or a new one created), it’s easy to do almost anything from inside it using the `%system` Java cell magic (thanks to the [author who was behind this feature](https://github.com/SpencerPark/IJava/pull/78)). 

We suggest open the [MyFirstJupyterNLPJavaNotebook.ipynb](https://github.com/neomatrix369/nlp-java-jvm-example/blob/integrating-nlp-java-with-jupyhai/shared/notebooks/MyFirstJupyterNLPJavaNotebook.ipynb) notebook and take a look at how we do things in there.

For e.g. imagine the below cell and actions performed in one of the cells:


    %system ../opennlp/opennlp.sh


    [<---snipped--->]
    apache-opennlp-1.9.1/lib/validation-api-1.1.0.Final.jar
    apache-opennlp-1.9.1/lib/jersey-media-json-jackson-2.25.jar
    apache-opennlp-1.9.1/lib/jersey-entity-filtering-2.25.jar
    apache-opennlp-1.9.1/lib/jackson-jaxrs-base-2.8.4.jar
    apache-opennlp-1.9.1/lib/jackson-core-2.8.4.jar
    apache-opennlp-1.9.1/lib/jackson-databind-2.8.4.jar
    apache-opennlp-1.9.1/lib/jackson-annotations-2.8.4.jar
    apache-opennlp-1.9.1/lib/jackson-jaxrs-json-provider-2.8.4.jar
    apache-opennlp-1.9.1/lib/jackson-module-jaxb-annotations-2.8.4.jar
    /home/jovyan/work
    Finished downloading and unpacking apache-opennlp-1.9.1-bin.tar.gz in /home/jovyan/work/apache-opennlp-1.9.1-bin

The above one-liner command in the cell results in the [Apache OpenNLP](https://opennlp.apache.org/) installed in the expected target folder.

Once done with the above steps, please return to the previous page/section.

## Viewing and accessing the shared folder

Just as you run the container,  a shared folder is created (or already present from previous executions), it won’t be empty but more files and folders will appear in there as we go along.

It’s also where you will find the downloaded models and the [Apache OpenNLP](https://opennlp.apache.org/) binary exploded into its own folder (by the name `apache-opennlp-1.9.1`). 

You can access and see the contents of it from your local machine command-prompt (outside the container) as well:

    ### Open a new command prompt
    $ cd nlp-java-jvm-example
    $ cd images/java/opennlp
    $ ls ..
    Dockerfile       corenlp.sh       opennlp          reverb.sh        word2vec.sh
    cogcomp-nlp.sh   mallet.sh        openregex.sh     shared
    common.sh        nlp4j.sh         rdrposttagger.sh version.txt
    
    $ ls ../shared
    apache-opennlp-1.9.1   en-ner-date.bin        en-sent.bin
    en-chunker.bin         en-parser-chunking.bin langdetect-183.bin
    
    ### In your case the contents of the shared folder may vary but the way to get to the folder is above.

From inside the container this is what you see (use the `docker exec` command to log into the current container session, see above):

    root@1bc4ba4e01f8:~$ ls 
    cogcomp-nlp.sh   corenlp.sh  nlp4j.sh  openregex.sh        reverb.sh  word2vec.sh
    common.sh        mallet.sh   opennlp   rdrposttagger.sh    shared     work
    
    root@1bc4ba4e01f8:~$ ls shared
    apache-opennlp-1.9.1   en-ner-time.bin        en-pos-perceptron.bin  langdetect-183.bin
    en-chunker.bin         en-parser-chunking.bin en-sent.bin            notebooks
    en-ner-date.bin        en-pos-maxent.bin      en-token.bin
    
    root@1bc4ba4e01f8:~$ ls shared/notebooks
    MyFirstJupyterJavaNotebook.ipynb
    
    ### In your case the contents of the above folders may vary but the way to get to the folder is above.

Once done with the above steps, please return to the previous page/section.

# Contributing

Contributions are very welcome, please share back with the wider community (and get credited for it)!

Please have a look at the [CONTRIBUTING](../../../CONTRIBUTING.md) guidelines, also have a read about our [licensing](../../../LICENSE.txt) policy.

---

Back to [main page (table of contents)](../../../README.md#nlp-javajvm-)

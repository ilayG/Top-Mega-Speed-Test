"# Top-Mega-Speed-Test" 

Android Internet Speed ​​Test — это проект с открытым исходным кодом, который предоставляет надежный и надежный API для внутреннего тестирования скорости на основе популярных серверов Ookla Speed ​​Test. С помощью этого API разработчики могут легко интегрировать функцию проверки скорости Интернета в свои приложения Android, предоставляя пользователям точный и всесторонний анализ производительности сети.

Одной из ключевых особенностей этого проекта является использование шаблона проектирования Builder для API теста скорости. Этот шаблон позволяет разработчикам создавать сложные объекты шаг за шагом, предоставляя им больший контроль над процессом создания и гарантируя включение всех необходимых компонентов. Это делает API более гибким и простым в использовании, а также снижает вероятность ошибок или несоответствий.

Проект Android Internet Speed ​​Test построен с упором на масштабируемость и удобство обслуживания, с использованием лучших практик и отраслевых стандартов для обеспечения высокой производительности и надежности. Это полностью открытый исходный код, поэтому разработчики могут бесплатно вносить свой вклад в проект и использовать его в своих приложениях.

Если вы ищете мощное и настраиваемое решение для проверки скорости Интернета для вашего приложения Android, не ищите ничего, кроме Android Internet Speed ​​Test. Благодаря надежному API и интуитивно понятному дизайну это идеальный выбор для разработчиков, которые хотят предоставить своим пользователям наилучшие возможности.



#### TestDownloader Builder


     mBuilderDownload = TestDownloader.Builder(url)
                .addListener(object : TestDownloader.TestDownloadListener {
                    override fun onStart() {
                       
                    }
    
                    override fun onProgress(progress: Double, elapsedTimeMillis: Double) {
                   
                    }
    
                    override fun onFinished(
                        finalprogress: Double,
                        datausedinkb: Int,
                        elapsedTimeMillis: Double
                    ) {
                       
                    }
    
                    override fun onError(msg: String) {
                       
                    }
    
                })
                .setTimeOUt(mTimeOut)
                .setThreadsCount(mConnectionType)
                .build()
            mBuilderDownload?.start()

#### TestUploader Builder

    mBuilderUpload = TestUploader.Builder(fullurl)
                .addListener(object : TestUploader.TestUploadListener {
                    override fun onStart() {
                      
                    }
    
                    override fun onProgress(progress: Double, elapsedTimeMillis: Double) {
                      
                    }
                    override fun onFinished(
                        finalprogress: Double,
                        datausedinkb: Int,
                        elapsedTimeMillis: Double
                    ) {
                        
                    }
    
                    override fun onError(msg: String) {
                      
                    }
    
                })
                .setTimeOUt(mTimeOut)
                .setThreadsCount(mConnectionType)
                .build()
            mBuilderUpload?.start()





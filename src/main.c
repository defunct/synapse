#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "mongoose.h"

static int exit_flag;                   /* Program termination flag     */

static void
signal_handler(int sig_num)
{
#if !defined(_WIN32)
        if (sig_num == SIGCHLD) {
                do {
                } while (waitpid(-1, &sig_num, WNOHANG) > 0);
        } else
#endif /* !_WIN32 */
        {
                exit_flag = sig_num;
        }
}


int
main(int argc, char *argv[])
{
    struct mg_context   *ctx;
    struct mg_config    config;

    (void) memset(&config, 0, sizeof(config));
    config.document_root = ".";
    config.enable_directory_listing = "yes";
    config.auth_domain = "mydomain.com";
    config.num_threads = "20";
    config.index_files = "index.html,index.htm,index.cgi";
    config.cgi_extensions = ".cgi,.pl,.php";
    config.ssi_extensions = ".shtml,.shtm";
    config.listening_ports = "8086";

        /* Setup signal handler: quit on Ctrl-C */
#ifndef _WIN32
        (void) signal(SIGCHLD, signal_handler);
#endif /* _WIN32 */
        (void) signal(SIGTERM, signal_handler);
        (void) signal(SIGINT, signal_handler);

    ctx = mg_start(&config);

    while (exit_flag == 0)
            sleep(1);

    (void) printf("Exiting on signal %d, "
        "waiting for all threads to finish...", exit_flag);
    fflush(stdout);
    mg_stop(ctx);
    (void) printf("%s", " done.\n");
    return (EXIT_SUCCESS);
}

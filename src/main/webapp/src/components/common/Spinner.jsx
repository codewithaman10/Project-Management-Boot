export default function Spinner() {
    return(
        <div class="min-h-[15rem] flex flex-col bg-transparent h-screen">
            <div class="flex flex-auto flex-col justify-center items-center p-4 md:p-5">
                <div class="flex justify-center">
                <div class="animate-spin inline-block w-6 h-6 border-[3px] border-current border-t-transparent  rounded-full" role="status" aria-label="loading">
                    <span class="sr-only">Loading...</span>
                </div>
                </div>
            </div>
        </div>
    );
}
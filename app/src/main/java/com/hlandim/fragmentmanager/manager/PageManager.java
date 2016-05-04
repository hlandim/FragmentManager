package com.hlandim.fragmentmanager.manager;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hlandim.fragmentmanager.NavigationException;
import com.hlandim.fragmentmanager.fragment.BaseFragment;
import com.hlandim.fragmentmanager.fragment.interfaces.FragmentStateListener;

/**
 * Created by hlandim on 4/5/16.
 */
public class PageManager implements FragmentStateListener {

    private static PageManager instance;
    // Armazena a tela atual
    private PageId currentPage;
    private AppCompatActivity activity;

    private PageManager() {
        currentPage = PageId.SPLASH;
    }

    public static PageManager getInstance() {
        if (instance == null) {
            instance = new PageManager();
        }
        return instance;
    }

    /**
     * Responsável por fazer a navegação da para a próxima tela;
     */
    public void goToPage(PageId nextPage) {

        if (nextPage != null) {

            try {
                //Pede a próxima página, caso sejá um fluxo inválido, a exceção NavigationException será lançada!
                TransitionPageConfig transitionFragment = currentPage.getNextPage(nextPage);
                if (transitionFragment != null) {
                    //Faz a próxima transição
                    makeTransition(nextPage, transitionFragment);
                    this.currentPage = nextPage;
                }

            } catch (NavigationException e) {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void makeTransition(PageId pageId, TransitionPageConfig transitionFragment) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        setCustomAnimations(transitionFragment, transaction);

        BaseFragment nextFragment = transitionFragment.getFragment();
        nextFragment.setFragmentStateListener(this);
        // Verifica se é necessário retirar o fragment_base atual para exibir o próximo
        if (transitionFragment.isRemoveCurrent()) {
            if (transitionFragment.isReplaceNext()) {
                transaction.replace(transitionFragment.getFragment_container(), nextFragment, pageId.name());
            }
            transaction.remove(currentPage.getFragment());
        } else {
            //caso contrário, basta apenas usar o replace
            transaction.replace(transitionFragment.getFragment_container(), nextFragment, pageId.name());
        }


        transaction.commit();
    }

    /**
     * Configura as animações entre os fragments
     *
     * @param transactionFragment
     * @param transaction
     */
    private void setCustomAnimations(TransitionPageConfig transactionFragment, FragmentTransaction transaction) {
        TransitionPageConfig.Animation animation = transactionFragment.getAnimation();
        if (animation != null) {
            int enter = animation.getInTransition();
            int exit = animation.getOutTransition();
            if (enter > 0 && exit > 0) {
                transaction.setCustomAnimations(enter, exit);
            }
        }
    }


    @Override
    public void onFragmentRemoved(BaseFragment frag) {
        PageId pageId = PageId.getByFragment(frag);
        if (pageId != null) {
            pageId.setFragment(null);
        }
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public PageId getCurrentPage() {
        return currentPage;
    }

    public void close(){
        instance = null;
    }
}
